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

import easyquery.client.etc.engine.Numeric;
import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ButtonQuery;
import easyquery.client.widget.system.form.ListBoxQuery;
import easyquery.client.widget.system.form.TextboxQuery;
import easyquery.client.widget.system.mainpanel.MainPanel;
import easyquery.client.widget.system.newmember.NewMember;

public class AddUser extends VerticalPanel {

	public AddUser(){
		final Error ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		final ListBoxQuery LBX1 = new ListBoxQuery(new Text().getText().GroupName(),"etc/lib/php/_private/SRV_GetListGroups.php");
		final TextboxQuery TXB1 = new TextboxQuery(new Text().getText().EmployeeNumberLabel());
		TXB1.setMaxLength(10);
		final ListBoxQuery LBX2 = new ListBoxQuery(new Text().getText().UserToBeAdmin());
		LBX2.List.addItem("no", "no");
		LBX2.List.addItem("yes", "yes");
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().RegisterLabel());
		
		add(ErrorPanel);
		add(LBX1);
		add(TXB1);
		add(LBX2);
		add(SBM);
		setCellHorizontalAlignment(SBM, ALIGN_RIGHT);
		
		SBM.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SBM.Loading(true);
				ErrorPanel.setVisible(false);
				if(!Numeric.isNumeric(TXB1.getText())){
					NewMember.getsingleton().ErrorPanel.setVisible(true);
					NewMember.getsingleton().ErrorPanel.setTextError(new Text().getText().ErrorNoNumericField());
				}else if(TXB1.getText().equals("") || LBX1.List.getValue(LBX1.List.getSelectedIndex()).equals("")){
					ErrorPanel.setVisible(true);
					ErrorPanel.setTextError(new Text().getText().ErrorEmptyField());
				}else{
					String UserAdmin;
					if(LBX2.List.getValue(LBX2.List.getSelectedIndex()).equals("yes")){
						UserAdmin = "yes";
					}else{
						UserAdmin = "no";
					}
					RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_RegisterUser.php"));
					REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
					try {
						REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&group="+LBX1.List.getValue(LBX1.List.getSelectedIndex())+"&employeenumber="+TXB1.getText()+"&admin="+UserAdmin, new RequestCallback() {
							
							@Override
							public void onResponseReceived(Request request, Response response) {
								SBM.Loading(false);
								if(response.getText().equals("OKREGISTERUSER")){
									Window.alert(new Text().getText().SuccesRegisterUser());
									MainPanel.getSingleton().Load_ListUser(LBX1.List.getValue(LBX1.List.getSelectedIndex()));
								}else if(response.getText().equals("ERRORUSEREXIST")){
									ErrorPanel.setTextError(new Text().getText().ErrorUserExist());
									ErrorPanel.setVisible(true);
									SBM.Loading(false);
								}else if(response.getText().equals("ERRORFIELDS")){
									ErrorPanel.setTextError(new Text().getText().ErrorIncorrectField());
									ErrorPanel.setVisible(true);
									SBM.Loading(false);
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
				}
			}
		});
	}
}
