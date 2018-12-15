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

package easyquery.client.widget.system.user;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import easyquery.client.etc.icon.Icon;
import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ListBoxQuery;
import easyquery.client.widget.system.list.List;
import easyquery.client.widget.system.list.ListHeaderRow;
import easyquery.client.widget.system.list.ListRow;
import easyquery.client.widget.system.mainpanel.MainPanel;

public class ListUser extends VerticalPanel {

	public Error ErrorPanel;
	public List ListUser;
	
	public ListUser(String Group_id){
		setWidth("100%");
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		
		ListUser = new List();
		
		final ListBoxQuery LBX1 = new ListBoxQuery(new Text().getText().GroupName(),"etc/lib/php/_private/SRV_GetListGroups.php");
		LBX1.List.addItem("", "-1");
		LBX1.List.addItem("All groups", "0");
		LBX1.setWidth("99%");
		add(LBX1);
		if(!Group_id.equals("")) { 
			//LBX1.List.setItemSelected(Integer.valueOf(Group_id).intValue()+1,true);
			LoadListUsers(Group_id);
		}
		LBX1.List.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				ListUser.clear();
				ListUser.ListHP.clear();
				if(!LBX1.List.getValue(LBX1.List.getSelectedIndex()).equals("-1")){
					LoadListUsers(LBX1.List.getValue(LBX1.List.getSelectedIndex()));
				}
			}
		});
		
		add(ListUser);
		setCellVerticalAlignment(ListUser, ALIGN_TOP);
		
	}
	
	public void LoadListUsers(final String Group_id){
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetUsers.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&groupid="+Group_id, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					//Window.alert(response.getText());
					if(response.getText().split("\\#")[0].equals("OK")){
						
						LinkedList<Label> Columns = new LinkedList<Label>();
						Columns.add(new Label(new Text().getText().List_EmployeeNumber()));
						Columns.add(new Label(new Text().getText().List_Options()));
						ListHeaderRow Header = new ListHeaderRow(Columns);
						ListUser.ListHP.add(Header);
						
						String[] Rows = response.getText().split("\\#");
						for(int x=1;x<Rows.length;x++){
							String[] value = Rows[x].split("\\|");
							final Hidden EmployeeNum = new Hidden();
							EmployeeNum.setValue(value[0]);
							final Label TEXT1 = new Label(value[0]);
							TEXT1.setStyleName("TextListRow");
							final Label TEXT2 = new Label(value[1]);
							TEXT2.setStyleName("TextListRow");
							final Label TEXT3 = new Label(value[2] + " " + value[3]);
							TEXT3.setStyleName("TextListRow");
							final Label TEXT5 = new Label(value[4]);
							TEXT5.setStyleName("TextListRow");
							Hidden UserActive = new Hidden();
							UserActive.setValue(value[5]);
							
							
							HorizontalPanel HP = new HorizontalPanel();
							HP.setWidth("100%");
							HP.add(TEXT1);
							HP.setCellHorizontalAlignment(TEXT1, ALIGN_LEFT);
							HP.setCellVerticalAlignment(TEXT1, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT1, "10%");
							HP.add(TEXT2);
							HP.setCellHorizontalAlignment(TEXT2, ALIGN_LEFT);
							HP.setCellVerticalAlignment(TEXT2, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT2, "5%");
							HP.add(TEXT3);
							HP.setCellHorizontalAlignment(TEXT3, ALIGN_CENTER);
							HP.setCellVerticalAlignment(TEXT3, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT3, "35%");
							HP.add(TEXT5);
							HP.setCellHorizontalAlignment(TEXT5, ALIGN_RIGHT);
							HP.setCellVerticalAlignment(TEXT5, ALIGN_MIDDLE);
							HP.setCellWidth(TEXT5, "30%");
							
							Widget[] widget = new Widget[1];
							widget[0] = HP;
							ListRow Row = new ListRow(widget);
							
							final Image Account = new Image(new Icon().Account);
							Account.setTitle(new Text().getText().UserDetails());
							Account.setStyleName("Icon");
							Account.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									MainPanel.getSingleton().Load_ViewUserForm(EmployeeNum.getValue());
								}
							});
							final Image Active = new Image(new Icon().Activated);
							Active.setTitle(new Text().getText().Action_UnLockUser());
							Active.setStyleName("Icon");
							final Image NoActive = new Image(new Icon().NoActivated);
							NoActive.setTitle(new Text().getText().Action_LockUser());
							NoActive.setStyleName("Icon");
							if(UserActive.getValue().contains("1")){ NoActive.setVisible(false); Active.setVisible(true); }
							else if(UserActive.getValue().contains("0")){ NoActive.setVisible(true); Active.setVisible(false); }
							
							NoActive.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									ErrorPanel.setVisible(false);
									RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_LockUser.php"));
									REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
									try {
										REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+TEXT1.getText()+"&lock=1", new RequestCallback() {
											
											@Override
											public void onResponseReceived(Request request, Response response) {
												//Window.alert(response.getText());
												if(response.getText().equals("OKUSERLOCK")){
													MainPanel.getSingleton().Load_ListUser(Group_id);
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
							Active.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									ErrorPanel.setVisible(false);
									RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_LockUser.php"));
									REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
									try {
										REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+TEXT1.getText()+"&lock=0", new RequestCallback() {
											
											@Override
											public void onResponseReceived(Request request, Response response) {
												//Window.alert(response.getText());
												if(response.getText().equals("OKUSERLOCK")){
													MainPanel.getSingleton().Load_ListUser(Group_id);
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
									if(Window.confirm(new Text().getText().Action_Delete())){
										RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_DeleteUser.php"));
										REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
										try {
											REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+EmployeeNum.getValue(), new RequestCallback() {
												
												@Override
												public void onResponseReceived(Request request, Response response) {
													if(response.getText().equals("OKDELETEUSER")){
														MainPanel.getSingleton().Load_ListUser(Group_id);
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
							Row.Options.add(Account);
							Row.Options.setCellHorizontalAlignment(Account, ALIGN_CENTER);
							Row.Options.add(Active);
							Row.Options.setCellHorizontalAlignment(Active, ALIGN_CENTER);
							Row.Options.add(NoActive);
							Row.Options.setCellHorizontalAlignment(NoActive, ALIGN_CENTER);
							Row.Options.add(Trash);
							Row.Options.setCellHorizontalAlignment(Trash, ALIGN_CENTER);
							
							ListUser.ListHP.add(Row);
						}
						
						ListUser.ShowList();
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
