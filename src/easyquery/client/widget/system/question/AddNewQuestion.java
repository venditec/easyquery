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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import easyquery.client.widget.system.form.ListBoxQuery;
import easyquery.client.widget.system.form.TextEditorQuery;
import easyquery.client.widget.system.form.TextboxQuery;
import easyquery.client.widget.system.mainpanel.MainPanel;

public class AddNewQuestion extends VerticalPanel {

	public Error ErrorPanel;
	public LinkedList<TextEditorQuery> Answers;
	
	public AddNewQuestion(){
		
		setWidth("100%");
		Answers = new LinkedList<TextEditorQuery>();
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		final VerticalPanel VP = new VerticalPanel();
		VP.setWidth("100%");
		
		add(ErrorPanel);
		
		final ListBoxQuery LBX1 = new ListBoxQuery(new Text().getText().Questionary());
		LBX1.List.addItem("", "-1");
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetQuestionaries.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode(), new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					if(response.getText().split("\\#")[0].equals("OK")){
						for(int i=1;i<response.getText().split("\\#").length;i++){
							LBX1.List.addItem(response.getText().split("\\#")[i].split("\\|")[1], response.getText().split("\\#")[i].split("\\|")[0]);
						}
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
		} catch (RequestException e) {
			e.printStackTrace();
			Window.alert("Error en servidor :: SRVLogicService");
		}
		
		LBX1.setWidth("99%");
		add(LBX1);
		add(VP);
		
		LBX1.List.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				VP.clear();
				if(!LBX1.List.getValue(LBX1.List.getSelectedIndex()).equals("-1")){
					VP.add(LoadFormWisywing(LBX1.List.getValue(LBX1.List.getSelectedIndex())));
				}
			}
		});
	}
	
	public VerticalPanel LoadFormWisywing(final String IdQuestionary){
		final VerticalPanel VP = new VerticalPanel();
		VP.setWidth("100%");
		final VerticalPanel VPAnswer = new VerticalPanel();
		VPAnswer.setWidth("100%");
		
		final TextEditorQuery TextQuestion = new TextEditorQuery(new Text().getText().Question());
		final TextboxQuery TXB1 = new TextboxQuery(new Text().getText().AnswerNumber());
		TXB1.setMaxLength(2);
		
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().OptionsAdmin_51()+" "+new Text().getText().OptionsAdmin_5());
		VP.add(TextQuestion);
		VP.add(TXB1);
			
		TXB1.getTextBox().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				VPAnswer.clear();
				Answers.clear();
				for(int i=0;i<Integer.valueOf(TXB1.getText()).intValue();i++){
					
					TextEditorQuery TexteditorAnswer = new TextEditorQuery(new Text().getText().Answer()+" num: "+(i+1));
					TexteditorAnswer.HP1.setStyleName("TextboxQueryAnswer1");
					TexteditorAnswer.HP2.setStyleName("TextboxQueryAnswer2");
					VPAnswer.add(TexteditorAnswer);
					VP.add(VPAnswer);
					Answers.add(TexteditorAnswer);
				}
				VP.add(SBM);
				VP.setCellHorizontalAlignment(SBM, ALIGN_RIGHT);
			}
		});
		
		SBM.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SBM.Loading(true);
				ErrorPanel.setVisible(false);
				if(!IdQuestionary.equals("-1")){
					if(!TextQuestion.Texteditor.getHTML().equals("")){
						if(Integer.valueOf(TXB1.getText()).intValue()>1){
							if(!isTextEditorAnswerNull(Answers)){
								String Answersvalues = "";
								for(int i=0;i<Answers.size();i++){
									Answersvalues = Answersvalues + "&answertext" + i + "=" + Answers.get(i).Texteditor.getHTML();
								}
								
								RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_AddNewQuestion.php"));
								REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
								try {
									REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary+"&questiontext="+TextQuestion.Texteditor.getHTML()+"&numanswers="+Answers.size()+Answersvalues, new RequestCallback() {
										
										@Override
										public void onResponseReceived(Request request, Response response) {
											//Window.alert(response.getText());
											if(response.getText().equals("OKADDQUESTION")){
												Window.alert(new Text().getText().SuccesAddQuestion());
												MainPanel.getSingleton().Load_ListQuestion(IdQuestionary);
											}else{
												ErrorPanel.setTextError(new Text().getText().ErrorServer());
												ErrorPanel.setVisible(true);
												SBM.Loading(false);
											}
										}
										
										@Override
										public void onError(Request request, Throwable exception) {
											Window.alert("Error en servidor :: SRVLogicService");	
										}
									});
									
								} catch (RequestException e) {
									e.printStackTrace();
									Window.alert("Error en servidor :: SRVLogicService");
								}
							}else{
								ErrorPanel.setTextError(new Text().getText().ErrorEmptyField());
								ErrorPanel.setVisible(true);
								SBM.Loading(false);
							}
						}else{
							ErrorPanel.setTextError(new Text().getText().ErrorNoAnswers());
							ErrorPanel.setVisible(true);
							SBM.Loading(false);
						}
					}else{
						ErrorPanel.setTextError(new Text().getText().ErrorEmptyField());
						ErrorPanel.setVisible(true);
						SBM.Loading(false);
					}
				}else{
					ErrorPanel.setTextError(new Text().getText().ErrorQuestionarySelected());
					ErrorPanel.setVisible(true);
					SBM.Loading(false);
				}
			}
		});
		
		return VP;
	}
	
	public boolean isTextEditorAnswerNull(LinkedList<TextEditorQuery> answers){
		for(int i=0;i<answers.size();i++){
			if(answers.get(i).Texteditor.getHTML().equals("")) return true;
		}
		return false;
	}
}
