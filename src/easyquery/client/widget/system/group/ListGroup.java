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

package easyquery.client.widget.system.group;

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
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
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

public class ListGroup extends VerticalPanel {

	public ListGroup(){
		
		setWidth("100%");
		
		final Error ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		
		final List ListGroup = new List();
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetGroups.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode(), new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(response.getText().split("\\#")[0].equals("OK")){
						
						LinkedList<Label> Columns = new LinkedList<Label>();
						Columns.add(new Label(new Text().getText().List_Name()));
						Columns.add(new Label(new Text().getText().List_Options()));
						ListHeaderRow Header = new ListHeaderRow(Columns);
						ListGroup.ListHP.add(Header);
						
						String[] Rows = response.getText().split("\\#");
						
						for(int x=1;x<Rows.length;x++){
							String[] value = Rows[x].split("\\|");
							final Hidden ID = new Hidden();
							ID.setValue(value[0]);
							final Label TEXT = new Label(value[1]);
							TEXT.setStyleName("TextListRow");
							final TextBox TXB = new TextBox();
							TXB.setText(value[1]);
							TXB.setStyleName("TextBoxList");
							TXB.setVisible(false);
							TEXT.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									TEXT.setVisible(false);
									TXB.setVisible(true);
								}
							});
							TXB.addChangeHandler(new ChangeHandler() {
								
								@Override
								public void onChange(ChangeEvent event) {
									ErrorPanel.setVisible(false);
									RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_UpdateGroup.php"));
									REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
									try {
										REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&id="+ID.getValue()+"&name="+TXB.getText(), new RequestCallback() {
											
											@Override
											public void onResponseReceived(Request request, Response response) {
												if(response.getText().split("\\|")[0].equals("OKUPDATEGROUP")){
													TEXT.setText(response.getText().split("\\|")[1]);
													TEXT.setVisible(true);
													TXB.setVisible(false);
													ErrorPanel.setVisible(false);
												}else if(response.getText().split("\\|")[0].equals("EMPTYFIELD")){
													ErrorPanel.setTextError(new Text().getText().ErrorEmptyField());
													ErrorPanel.setVisible(true);
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
							HorizontalPanel HP = new HorizontalPanel();
							HP.setWidth("100%");
							HP.add(ID);
							HP.add(TEXT);
							HP.setCellHorizontalAlignment(TEXT, ALIGN_CENTER);
							HP.setCellVerticalAlignment(TEXT, ALIGN_MIDDLE);
							HP.add(TXB);
							HP.setCellHorizontalAlignment(TXB, ALIGN_CENTER);
							HP.setCellVerticalAlignment(TXB, ALIGN_MIDDLE);
							
							Widget[] widget = new Widget[1];
							widget[0] = HP;
							ListRow Row = new ListRow(widget);
							
							Image Employees = new Image(new Icon().IconEmployees);
							Employees.setTitle(new Text().getText().Action_AsignGroup());
							Employees.setStyleName("Icon");
							Employees.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									MainPanel.getSingleton().Load_GroupAssign(ID.getValue(), TEXT.getText());
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
										RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_DeleteGroup.php"));
										REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
										try {
											REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&id="+ID.getValue(), new RequestCallback() {
												
												@Override
												public void onResponseReceived(Request request, Response response) {
													if(response.getText().equals("OKDELETEGROUP")){
														MainPanel.getSingleton().Load_ListGroup();
													}else if(response.getText().equals("USERSINGROUP")){
														ErrorPanel.setTextError(new Text().getText().ErrorUsersInGroup());
														ErrorPanel.setVisible(true);
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
							Row.Options.add(Employees);
							Row.Options.setCellHorizontalAlignment(Employees, ALIGN_CENTER);
							Row.Options.add(Trash);
							Row.Options.setCellHorizontalAlignment(Trash, ALIGN_CENTER);
							
							ListGroup.ListHP.add(Row);
						}
						
						ListGroup.ShowList();
						add(ListGroup);
						setCellVerticalAlignment(ListGroup, ALIGN_TOP);
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
