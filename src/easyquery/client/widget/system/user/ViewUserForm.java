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
import easyquery.client.widget.system.form.TextboxQuery;

public class ViewUserForm extends VerticalPanel {

	public ViewUserForm(final String EmployeeNumber){
		
		final Error ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		final TextboxQuery TXB1 = new TextboxQuery(new Text().getText().Name());
		TXB1.setMaxLength(20);
		final TextboxQuery TXB2 = new TextboxQuery(new Text().getText().Surname());
		TXB2.setMaxLength(40);
		final TextboxQuery TXB3 = new TextboxQuery(new Text().getText().Mail());
		TXB3.setMaxLength(80);
		final TextboxQuery TXB8 = new TextboxQuery(new Text().getText().PrivateMail());
		TXB8.setMaxLength(80);
		final TextboxQuery TXB4 = new TextboxQuery(new Text().getText().Address());
		final TextboxQuery TXB5 = new TextboxQuery(new Text().getText().City());
		TXB5.setMaxLength(50);
		final TextboxQuery TXB6 = new TextboxQuery(new Text().getText().PostalCode());
		TXB6.setMaxLength(15);
		final TextboxQuery TXB9 = new TextboxQuery(new Text().getText().Country());
		TXB9.setMaxLength(50);
		final TextboxQuery TXB7 = new TextboxQuery(new Text().getText().Phone());
		TXB7.setMaxLength(20);
		
		TXB1.getTextBox().setEnabled(false);
		TXB2.getTextBox().setEnabled(false);
		TXB3.getTextBox().setEnabled(false);
		TXB8.getTextBox().setEnabled(false);
		TXB4.getTextBox().setEnabled(false);
		TXB5.getTextBox().setEnabled(false);
		TXB6.getTextBox().setEnabled(false);
		TXB9.getTextBox().setEnabled(false);
		TXB7.getTextBox().setEnabled(false);
		
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
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetInfoUser.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+EmployeeNumber, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(!response.getText().split("\\|")[0].equals("OK")){
						ErrorPanel.setVisible(true);
						ErrorPanel.setTextError(new Text().getText().ErrorServer());
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
