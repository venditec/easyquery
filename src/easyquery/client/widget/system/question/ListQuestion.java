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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import easyquery.client.etc.engine.LightColor;
import easyquery.client.etc.icon.Icon;
import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ListBoxQuery;
import easyquery.client.widget.system.list.List;
import easyquery.client.widget.system.list.ListHeaderRow;
import easyquery.client.widget.system.list.ListRow;
import easyquery.client.widget.system.mainpanel.MainPanel;

public class ListQuestion extends VerticalPanel {
	
	public Error ErrorPanel;
	public List ListQuestion;
	
	public ListQuestion(String IdQuestionary){
		setWidth("100%");
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		
		ListQuestion = new List();
		
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
		if(!IdQuestionary.equals("")) { 
			//LBX1.List.setItemSelected(Integer.valueOf(IdQuestionary).intValue()+1,true);
			LoadListQuestions(IdQuestionary);
		}
		LBX1.List.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				ListQuestion.clear();
				ListQuestion.ListHP.clear();
				if(!LBX1.List.getValue(LBX1.List.getSelectedIndex()).equals("-1")){
					LoadListQuestions(LBX1.List.getValue(LBX1.List.getSelectedIndex()));
				}
			}
		});
		
		add(ListQuestion);
		setCellVerticalAlignment(ListQuestion, ALIGN_TOP);
		
	}
	
	public void LoadListQuestions(final String IdQuestionary){
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetQuestions.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					//Window.alert(response.getText());
					if(response.getText().split("\\#")[0].equals("OK")){
						
						LinkedList<Label> Columns = new LinkedList<Label>();
						Columns.add(new Label(new Text().getText().List_Question()));
						Columns.add(new Label(new Text().getText().List_Options()));
						ListHeaderRow Header = new ListHeaderRow(Columns);
						ListQuestion.ListHP.add(Header);
						
						String[] Rows = response.getText().split("\\#");
						for(int x=1;x<Rows.length;x++){
							String[] value = Rows[x].split("\\|");
							final Hidden QuestionID = new Hidden();
							QuestionID.setValue(value[0]);
							final HTML TEXT1 = new HTML(value[1]);
							TEXT1.setStyleName("TextListRow");
							
							VerticalPanel VP = new VerticalPanel();
							VP.setWidth("100%");
							
							VP.add(TEXT1);
							VP.setCellHorizontalAlignment(TEXT1, ALIGN_CENTER);
							VP.setCellVerticalAlignment(TEXT1, ALIGN_MIDDLE);
							
							final VerticalPanel VPANSWERS = new VerticalPanel();
							VPANSWERS.setWidth("100%");
							VPANSWERS.setVisible(false);
							for(int z=2;z<value.length;z=z+2){
								final HTML TEXTANSWER = new HTML(value[z+1]);
								TEXTANSWER.setStyleName("TextListRowAnswers");
								VPANSWERS.add(TEXTANSWER);
								VPANSWERS.setCellHorizontalAlignment(TEXTANSWER, ALIGN_CENTER);
								VPANSWERS.setCellVerticalAlignment(TEXTANSWER, ALIGN_MIDDLE);
							}
							
							VP.add(VPANSWERS);

							Widget[] widget = new Widget[1];
							widget[0] = VP;
							ListRow Row = new ListRow(widget);
							
							Image AnswerVisible = new Image(new Icon().AnswerVisible);
							AnswerVisible.setTitle(new Text().getText().Action_ViewAnswers());
;							AnswerVisible.setStyleName("Icon");
							final LightColor Open = new LightColor(0);
							AnswerVisible.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									if(Open.getValue() == 0){
										Open.setValue(1);
										VPANSWERS.setVisible(true);
									}else{
										Open.setValue(0);
										VPANSWERS.setVisible(false);
									}
									
								}
							});
							Image Edit = new Image(new Icon().IconEdit);
							Edit.setTitle(new Text().getText().Action_EditObject());
							Edit.setStyleName("Icon");
							Edit.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									MainPanel.getSingleton().Load_EditQuestion(QuestionID.getValue(), IdQuestionary);
								}
							});
							Image Trash = new Image(new Icon().IconTrash);
							Trash.setTitle(new Text().getText().Action_DeleteObject());
							Trash.setStyleName("Icon");
							Trash.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									ErrorPanel.setVisible(false);
									if(Window.confirm(new Text().getText().Action_Delete())){
										RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_DeleteQuestion.php"));
										REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
										try {
											REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestion="+QuestionID.getValue(), new RequestCallback() {
												
												@Override
												public void onResponseReceived(Request request, Response response) {
													//Window.alert(response.getText());
													if(response.getText().equals("OKDELETEQUESTION")){
														MainPanel.getSingleton().Load_ListQuestion(IdQuestionary);
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
							Row.Options.add(AnswerVisible);
							Row.Options.setCellHorizontalAlignment(AnswerVisible, ALIGN_CENTER);
							Row.Options.add(Edit);
							Row.Options.setCellHorizontalAlignment(Edit, ALIGN_CENTER);
							Row.Options.add(Trash);
							Row.Options.setCellHorizontalAlignment(Trash, ALIGN_CENTER);
							
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
	}
}
