/*
.---------------------------------------------------------------------------.
|  Software: EasyQuery                                  					|
|   Version: 1.0                                                            |
|   Contact: via sourceforge.net or ramon.sepi@gmail.com                    |
|      Info: http://sourceforge.net/projects/easyquery1/                    |
|   Support: http://sourceforge.net/projects/easyquery1/                    |
| ------------------------------------------------------------------------- |
|     Admin: Ramon Segarra Pijuan (project admininistrator)                 |
|   Author : Ramon Segarra Pijuan 											|
|   Founder: Ramon Segarra Pijuan (original founder)                        |
| Copyright (c) 2012-2013, Ramon Segarra Pijuan. All Rights Reserved.       |
| ------------------------------------------------------------------------- |
|   License: Distributed under the Creative Commons 						|
|            Attribution-NonCommercial 3.0 Unported License.     			|
|            http://creativecommons.org/licenses/by-nc/3.0/                 |
| This program is distributed in the hope that it will be useful -          |
| YOU ARE FREE: to Share — to copy, distribute and transmit the work.       |
|               to Remix — to adapt the work.								|
| UNDER THE FOLLOWING CONDITIONS: 											|
|		Noncomercial - You may not use this work for commercial purposes.	|
|		Attribution  — You must attribute the work to Ramon Segarra Pijuan	|
|                      like the author or licensor (but not in any way that |
|					   suggests that they endorse you or your use 			|
|                      of the work).										|
| ------------------------------------------------------------------------- |
|          EasyQuery by Ramon Segarra Pijuan is licensed under a 			|
|     Creative Commons Attribution-NonCommercial 3.0 Unported License.		|
'---------------------------------------------------------------------------'
*/

package easyquery.client.widget.system.question;

import java.util.LinkedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ButtonQuery;
import easyquery.client.widget.system.form.TextEditorQuery;
import easyquery.client.widget.system.mainpanel.MainPanel;


public class EditQuestion extends VerticalPanel {

	public Error ErrorPanel;
	public LinkedList<TextEditorQuery> Answers;
	
	public EditQuestion(final String IdQuestion, final String IdQuestionary){
		
		setWidth("100%");
		Answers = new LinkedList<TextEditorQuery>();
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		
		final TextEditorQuery TextQuestion = new TextEditorQuery(new Text().getText().Question());
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().Update());
		add(TextQuestion);
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetQuestion.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestion="+IdQuestion, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					//Window.alert(response.getText());
					if(response.getText().split("\\#")[0].equals("OK")){
						TextQuestion.Texteditor.setHTML(response.getText().split("\\#")[1].split("\\|")[1]);
						for(int i=2;i<response.getText().split("\\#").length;i++){
							TextEditorQuery TextAnswer = new TextEditorQuery(new Text().getText().Answer()+(i-1));
							TextAnswer.setIdentify(response.getText().split("\\#")[i].split("\\|")[0]);
							TextAnswer.Texteditor.setHTML(response.getText().split("\\#")[i].split("\\|")[1]);
							Answers.add(TextAnswer);
						}
						for(int j=0;j<Answers.size();j++){
							add(Answers.get(j));
						}
						add(SBM);
						setCellHorizontalAlignment(SBM, ALIGN_RIGHT);
					}else{
						ErrorPanel.setTextError(new Text().getText().ErrorServer());
						ErrorPanel.setVisible(true);
					}
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					Window.alert("Error en servidor :: SRVLogicService");	
				}
			});
		
			for(int j=0;j<Answers.size();j++){
				add(Answers.get(j));
			}
		} catch (RequestException e) {
			e.printStackTrace();
			Window.alert("Error en servidor :: SRVLogicService");
		}
		
		SBM.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ErrorPanel.setVisible(false);
				SBM.Loading(true);
				if(!TextQuestion.Texteditor.getHTML().equals("") && !isTextEditorAnswerNull(Answers)){
					
					String Answersvalues = "";
					for(int x=0;x<Answers.size();x++){
						Answersvalues = Answersvalues + "&answertext" + x + "=" + Answers.get(x).Texteditor.getHTML() + "&answerid" + x + "=" + Answers.get(x).getIdentify();
					}
					
					RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_UpdateQuestion.php"));
					REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
					try {
						REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestion="+IdQuestion+"&questiontext="+TextQuestion.Texteditor.getHTML()+"&numanswers="+Answers.size()+Answersvalues, new RequestCallback() {
							
							@Override
							public void onResponseReceived(Request request, Response response) {
								//Window.alert(response.getText());
								if(response.getText().equals("OKUPDATEQUESTION")){
									Window.alert(new Text().getText().SuccesUpdateQuestion());
									MainPanel.getSingleton().Load_ListQuestion(IdQuestionary);
								}else{
									ErrorPanel.setTextError(new Text().getText().ErrorServer());
									ErrorPanel.setVisible(true);
									SBM.Loading(true);
								}
							}
							
							@Override
							public void onError(Request request, Throwable exception) {
								Window.alert("Error en servidor :: SRVLogicService");
								SBM.Loading(true);
							}
						});
					
						for(int j=0;j<Answers.size();j++){
							add(Answers.get(j));
						}
					} catch (RequestException e) {
						e.printStackTrace();
						Window.alert("Error en servidor :: SRVLogicService");
						SBM.Loading(true);
					}
					
				}else{
					ErrorPanel.setTextError(new Text().getText().ErrorEmptyField());
					ErrorPanel.setVisible(true);
					SBM.Loading(true);
				}
			}
		});
	}
	
	public boolean isTextEditorAnswerNull(LinkedList<TextEditorQuery> answers){
		for(int i=0;i<answers.size();i++){
			if(answers.get(i).Texteditor.getHTML().equals("")) return true;
		}
		return false;
	}
}
