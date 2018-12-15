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
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import easyquery.client.etc.icon.Icon;
import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.list.List;
import easyquery.client.widget.system.list.ListHeaderRow;
import easyquery.client.widget.system.list.ListRow;
import easyquery.client.widget.system.mainpanel.MainPanel;

public class ListQuestionaryResult extends VerticalPanel {
	
	public ListQuestionaryResult(){
		setWidth("100%");
		
		final Error ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		
		final List ListQuestionary = new List();
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetQuestionariesResult.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode(), new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					//Window.alert(response.getText());
					ErrorPanel.setVisible(false);
					if(response.getText().split("\\#")[0].equals("OK")){
						
						LinkedList<Label> Columns = new LinkedList<Label>();
						Columns.add(new Label(new Text().getText().List_Name()));
						Columns.add(new Label(new Text().getText().List_Description()));
						Columns.add(new Label(new Text().getText().List_DateIni()));
						Columns.add(new Label(new Text().getText().List_DateEnd()));
						Columns.add(new Label(new Text().getText().List_Options()));
						ListHeaderRow Header = new ListHeaderRow(Columns);
						Header.setCellWidth(Header.Columns.get(0), "30%");
						Header.setCellWidth(Header.Columns.get(1), "20%");
						Header.setCellWidth(Header.Columns.get(2), "15%");
						Header.setCellWidth(Header.Columns.get(3), "15%");
						ListQuestionary.ListHP.add(Header);
						
						String[] Rows = response.getText().split("\\#");
						for(int x=1;x<Rows.length;x++){
							String[] value = Rows[x].split("\\|");
							final Hidden IdQuestionary = new Hidden();
							IdQuestionary.setValue(value[0]);
							final Label TEXT1 = new Label(value[1]);
							TEXT1.setStyleName("TextListRow0");
							final Label TEXT2 = new Label(value[4]);
							TEXT2.setStyleName("TextListRow1");
							final Label TEXT3 = new Label(value[2]);
							TEXT3.setStyleName("TextListRow2");
							final Label TEXT4 = new Label(value[3]);
							TEXT4.setStyleName("TextListRow3");
							final Hidden Lock = new Hidden();
							Lock.setValue(value[5]);
							
							HorizontalPanel HP = new HorizontalPanel();
							HP.setWidth("100%");
							HP.add(TEXT1);
							HP.setCellHorizontalAlignment(TEXT1, ALIGN_CENTER);
							HP.setCellVerticalAlignment(TEXT1, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT1, "30%");
							HP.add(TEXT2);
							HP.setCellHorizontalAlignment(TEXT2, ALIGN_CENTER);
							HP.setCellVerticalAlignment(TEXT2, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT2, "20%");
							HP.add(TEXT3);
							HP.setCellHorizontalAlignment(TEXT3, ALIGN_CENTER);
							HP.setCellVerticalAlignment(TEXT3, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT3, "15%");
							HP.add(TEXT4);
							HP.setCellHorizontalAlignment(TEXT4, ALIGN_CENTER);
							HP.setCellVerticalAlignment(TEXT4, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT4, "15%");
							
							Widget[] widget = new Widget[1];
							widget[0] = HP;
							ListRow Row = new ListRow(widget);
							
							Image Edit = new Image(new Icon().Statistics);
							Edit.setTitle(new Text().getText().Action_ViewResults());
							Edit.setStyleName("Icon");
							Edit.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									MainPanel.getSingleton().Load_ListResult(IdQuestionary.getValue(), TEXT1.getText());
								}
							});
							final Image PublicQuestionary = new Image(new Icon().Public);
							PublicQuestionary.setTitle(new Text().getText().Action_UnLockObject());
							PublicQuestionary.setStyleName("Icon");
							final Image LockQuestionary = new Image(new Icon().Lock);
							LockQuestionary.setTitle(new Text().getText().Action_LockObject());
							LockQuestionary.setStyleName("Icon");
							if(Lock.getValue().contains("1")){ PublicQuestionary.setVisible(false); LockQuestionary.setVisible(true); }
							else if(Lock.getValue().contains("0")){ PublicQuestionary.setVisible(true); LockQuestionary.setVisible(false); }
							PublicQuestionary.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									ErrorPanel.setVisible(false);
									RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_LockQuestionaryResults.php"));
									REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
									try {
										REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary.getValue()+"&lock=1", new RequestCallback() {
											
											@Override
											public void onResponseReceived(Request request, Response response) {
												//Window.alert(response.getText());
												if(response.getText().equals("OKLOCK")){
													MainPanel.getSingleton().Load_QuestionaryResult();
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
							});
							LockQuestionary.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									ErrorPanel.setVisible(false);
									RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_LockQuestionaryResults.php"));
									REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
									try {
										REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary.getValue()+"&lock=0", new RequestCallback() {
											
											@Override
											public void onResponseReceived(Request request, Response response) {
												//Window.alert(response.getText());
												if(response.getText().equals("OKLOCK")){
													MainPanel.getSingleton().Load_QuestionaryResult();
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
							});
							Image Trash = new Image(new Icon().IconTrash);
							Trash.setTitle(new Text().getText().Action_DeleteObject());
							Trash.setStyleName("Icon");
							Trash.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									ErrorPanel.setVisible(false);
									if(Lock.getValue().contains("1")){
										if(Window.confirm(new Text().getText().Action_Delete())){
											RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_DeleteQuestionaryResults.php"));
											REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
											try {
												REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary.getValue(), new RequestCallback() {
													
													@Override
													public void onResponseReceived(Request request, Response response) {
														//Window.alert(response.getText());
														if(response.getText().equals("OKDELETERESULTS")){
															MainPanel.getSingleton().Load_QuestionaryResult();
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
									}else{
										ErrorPanel.setTextError(new Text().getText().ErrorQuestionaryPublic());
										ErrorPanel.setVisible(true);
									}
								}
							});
							Row.Options.add(Edit);
							Row.Options.setCellHorizontalAlignment(Edit, ALIGN_CENTER);
							Row.Options.add(PublicQuestionary);
							Row.Options.setCellHorizontalAlignment(PublicQuestionary, ALIGN_CENTER);
							Row.Options.add(LockQuestionary);
							Row.Options.setCellHorizontalAlignment(LockQuestionary, ALIGN_CENTER);
							Row.Options.add(Trash);
							Row.Options.setCellHorizontalAlignment(Trash, ALIGN_CENTER);
							
							ListQuestionary.ListHP.add(Row);
						}
						
						ListQuestionary.ShowList();
						add(ListQuestionary);
						setCellVerticalAlignment(ListQuestionary, ALIGN_TOP);
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
