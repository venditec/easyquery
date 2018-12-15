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

package easyquery.client.widget.system.auth;

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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.EasyQuery;
import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ButtonQuery;
import easyquery.client.widget.system.form.TextboxQuery;

public class Auth extends VerticalPanel {
	
	public Auth(){
		
		setStyleName("Form1");
		
		final Error ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		final TextboxQuery TXB1 = new TextboxQuery(new Text().getText().EmployeeNumberLabel());
		add(TXB1);
		final TextboxQuery TXB2 = new TextboxQuery(new Text().getText().EmployeePasswordLabel(),new Text().getText().ShowTextBoxPassword());
		add(TXB2);
		
		HorizontalPanel HP = new HorizontalPanel();
		HP.setWidth("100%");
		
		Label L1 = new Label(new Text().getText().RememberPasswordLabel());
		L1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				EasyQuery.getSingleton().BODY.LoadFormRemember();
			}
		});
		Label L2 = new Label(new Text().getText().CreateAccountLabel());
		L2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				EasyQuery.getSingleton().BODY.LoadFormNewMember();
			}
		});
		Label LP = new Label(":-:");
		L1.setStyleName("NormalTextLink");
		L2.setStyleName("NormalTextLink");
		LP.setStyleName("Space");
		
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().AuthButton());
		SBM.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SBM.Loading(true);
				ErrorPanel.setVisible(false);
				RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/public/SRV_AuthenticationLogic.php"));
				REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
				try {
					REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+TXB1.getText()+"&password="+TXB2.PasswordTextboxValue.getText(), new RequestCallback() {
						
						@Override
						public void onResponseReceived(Request request, Response response) {
							//Window.alert(response.getText());
							SBM.Loading(false);
							if(response.getText().split("\\|")[0].equals("OKAUTH")){
								EasyQuery.getSingleton().FOOTER.setVisible(false);
								EasyQuery.getSingleton().HEAD.Title.setStyleName("TitleEasyQueryMini");
								EasyQuery.getSingleton().HEAD.setStyleName("HeadMini");
								EasyQuery.getSingleton().BODY.LoadPanel(TXB1.getText(),response.getText().split("\\|")[1]);
								RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_LockUser.php"));
								REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
								try {
									REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+TXB1.getText()+"&lock=1", new RequestCallback() {
										
										@Override
										public void onResponseReceived(Request request, Response response) {
											/* DB User Activated */
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
							}else if(response.getText().split("\\|")[0].equals("USERACTIVATED")){
								ErrorPanel.setTextError(new Text().getText().ErrorAuth2());
								ErrorPanel.setVisible(true);
							}else{
								ErrorPanel.setTextError(new Text().getText().ErrorAuth1());
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
		
		HP.add(L1);
		HP.setCellVerticalAlignment(L1, ALIGN_BOTTOM);
		HP.setCellHorizontalAlignment(L1, ALIGN_RIGHT);
		HP.add(LP);
		HP.setCellVerticalAlignment(LP, ALIGN_BOTTOM);
		HP.setCellHorizontalAlignment(LP, ALIGN_CENTER);
		HP.add(L2);
		HP.setCellVerticalAlignment(L2, ALIGN_BOTTOM);
		HP.setCellHorizontalAlignment(L2, ALIGN_LEFT);
		HP.add(SBM);
		HP.setCellHorizontalAlignment(SBM, ALIGN_RIGHT);
		
		add(HP);
		setCellHorizontalAlignment(HP, ALIGN_RIGHT);
	}

}
