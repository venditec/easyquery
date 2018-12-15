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

package easyquery.client.widget.system.questionary;


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
import com.google.gwt.user.client.ui.Widget;

import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ButtonQuery;
import easyquery.client.widget.system.list.List;
import easyquery.client.widget.system.list.ListRowTest;
import easyquery.client.widget.system.mainpanel.MainPanel;
import easyquery.client.widget.system.questionary.etc.QuestionBean;

public class DoMakeTest extends VerticalPanel {
	
	public Error ErrorPanel;
	public List ListQuestion;
	public LinkedList<QuestionBean> Questions;

	public DoMakeTest(final String Employee, final String IdQuestionary){
		
		setWidth("100%");
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		
		ListQuestion = new List();
		Questions = new LinkedList<QuestionBean>();
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().SendTest());
		
		add(ListQuestion);
		add(SBM);
		setCellHorizontalAlignment(SBM, ALIGN_CENTER);
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetQuestions.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					//Window.alert(response.getText());
					if(response.getText().split("\\#")[0].equals("OK")){
						
						String[] Rows = response.getText().split("\\#");
						for(int x=1;x<Rows.length;x++){
							String[] value = Rows[x].split("\\|");
							QuestionBean question = new QuestionBean(value);
							Questions.add(question);
						}
						
						for(int i=0;i<Questions.size();i++){
							Widget[] widget = new Widget[1];
							widget[0] = Questions.get(i);
							ListRowTest Row = new ListRowTest(widget);
							Row.setStyleName("QuestionTestStyle");
							ListQuestion.ListHP.add(Row);
						}
						
						ListQuestion.ShowList();
						ErrorPanel.setVisible(false);
						
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
		
		SBM.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm(new Text().getText().Action_SendTest())){
					RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_SaveResultTest.php"));
					REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
					try {
						String questionsListRequest = "";
						for(int i=0;i<Questions.size();i++){
							String IDAnswerselected = "";
							if(!Questions.get(i).getIdSelected().equals("NOSELECTED")){
								IDAnswerselected = Questions.get(i).getIdSelected();
							}else{
								IDAnswerselected = "";
							}
							questionsListRequest = questionsListRequest + "&idquestion"+i+"=" + Questions.get(i).QuestionID + "&answerselected"+i+"=" + IDAnswerselected;
						}
						REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary=" + IdQuestionary + "&employee=" + Employee + "&numquestions=" + Questions.size() + questionsListRequest, new RequestCallback() {
							
							@Override
							public void onResponseReceived(Request request, Response response) {
								ErrorPanel.setVisible(false);
								//Window.alert(response.getText());
								if(response.getText().equals("OKSAVERESULTTEST")){
									Window.alert(new Text().getText().SuccesSendTest());
									MainPanel.getSingleton().Load_QuestionaryListMember();
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
				}
			}
		});
	}
}
