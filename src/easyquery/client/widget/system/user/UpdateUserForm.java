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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.etc.lang.Text;
import easyquery.client.etc.lockmail.LockMail;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ButtonQuery;
import easyquery.client.widget.system.form.TextboxQuery;
import easyquery.client.widget.system.mainpanel.MainPanel;

public class UpdateUserForm extends VerticalPanel {

	public UpdateUserForm(final String EmployeeNumber){
		
		final Error ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		final TextboxQuery TXB1 = new TextboxQuery(new Text().getText().Name());
		TXB1.setMaxLength(20);
		final TextboxQuery TXB2 = new TextboxQuery(new Text().getText().Surname());
		TXB2.setMaxLength(40);
		final TextboxQuery TXB3 = new TextboxQuery(new Text().getText().Mail());
		TXB3.setMaxLength(80);
		TXB3.getTextBox().addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(TXB3.getTextBox().getText().length()<=0){
					TXB3.getTextBox().setText(new LockMail().getMailDomain());
					TXB3.getTextBox().setCursorPos(TXB3.getTextBox().getText().indexOf("@"));
				}else{
					String OldValue = TXB3.getTextBox().getText().substring(0, TXB3.getTextBox().getText().indexOf("@"));
					TXB3.getTextBox().setText(OldValue+new LockMail().getMailDomain());
					TXB3.getTextBox().setCursorPos(TXB3.getTextBox().getText().indexOf("@"));
				}
			}
		});
		final TextboxQuery TXB8 = new TextboxQuery(new Text().getText().PrivateMail());
		TXB8.setMaxLength(80);
		TXB8.getTextBox().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				ErrorPanel.setVisible(false);
				if(!TXB8.getText().contains("@") || !TXB8.getText().contains(".")){
					ErrorPanel.setVisible(true);
					ErrorPanel.setTextError(new Text().getText().ErrorMail());
				}else{
					if(ErrorPanel.isVisible())
					ErrorPanel.setVisible(false);
				}
			}
		});
		final TextboxQuery TXB4 = new TextboxQuery(new Text().getText().Address());
		final TextboxQuery TXB5 = new TextboxQuery(new Text().getText().City());
		TXB5.setMaxLength(50);
		final TextboxQuery TXB6 = new TextboxQuery(new Text().getText().PostalCode());
		TXB6.setMaxLength(15);
		final TextboxQuery TXB9 = new TextboxQuery(new Text().getText().Country());
		TXB9.setMaxLength(50);
		final TextboxQuery TXB7 = new TextboxQuery(new Text().getText().Phone());
		TXB7.setMaxLength(20);
		
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().Update());
		
		add(ErrorPanel);
		add(TXB1);
		add(TXB2);
		add(TXB3);
		add(TXB8);
		add(TXB4);
		add(TXB5);
		add(TXB6);
		add(TXB9);
		add(TXB7);
		add(SBM);
		setCellHorizontalAlignment(SBM, ALIGN_RIGHT);
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetInfoUser.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+EmployeeNumber, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(!response.getText().split("\\|")[0].equals("OK")){
						SBM.getButton().setEnabled(false);
					}else{
						TXB1.setText(response.getText().split("\\|")[1]);
						TXB2.setText(response.getText().split("\\|")[2]);
						TXB3.setText(response.getText().split("\\|")[3]);
						TXB8.setText(response.getText().split("\\|")[4]);
						TXB4.setText(response.getText().split("\\|")[5]);
						TXB5.setText(response.getText().split("\\|")[6]);
						TXB6.setText(response.getText().split("\\|")[7]);
						TXB9.setText(response.getText().split("\\|")[8]);
						TXB7.setText(response.getText().split("\\|")[9]);
						SBM.getButton().addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								SBM.Loading(true);
								ErrorPanel.setVisible(false);
								/*if(!Numeric.isNumeric(TXB7.getText())){
									ErrorPanel.setVisible(true);
									ErrorPanel.setTextError(new Text().getText().ErrorNoNumericField());
									SBM.Loading(false);
								}else*/ if(TXB1.getText().equals("") || TXB2.getText().equals("") || TXB3.getText().equals("") || TXB4.getText().equals("") || TXB5.getText().equals("") || TXB6.getText().equals("") || TXB7.getText().equals("") || TXB8.getText().equals("") || TXB9.getText().equals("")){
									ErrorPanel.setVisible(true);
									ErrorPanel.setTextError(new Text().getText().ErrorEmptyField());
									SBM.Loading(false);
								}else{
									RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_UpdateUser.php"));
									REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
									try {
										REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+EmployeeNumber+"&name="+TXB1.getText()+"&surname="+TXB2.getText()+"&mail="+TXB3.getText()+"&address="+TXB4.getText()+"&city="+TXB5.getText()+"&postalcode="+TXB6.getText()+"&country="+TXB9.getText()+"&privatemail="+TXB8.getText()+"&phone="+TXB7.getText(), new RequestCallback() {
											
											@Override
											public void onResponseReceived(Request request, Response response) {
												SBM.Loading(false);
												if(response.getText().equals("OKUPDATE")){
													Window.alert(new Text().getText().SuccesUpdateUser());
													MainPanel.getSingleton().Load_UpdateUserForm(InfoUser.getSingleton().EmployeeNumber);
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
