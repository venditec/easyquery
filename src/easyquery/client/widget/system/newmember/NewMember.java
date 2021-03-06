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

package easyquery.client.widget.system.newmember;

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

import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.newmember.elems.FormNewMember;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ButtonQuery;
import easyquery.client.widget.system.form.TextboxQuery;

public class NewMember extends VerticalPanel {
	
	public Error ErrorPanel;
	private static NewMember singleton;
	
	public static NewMember getsingleton(){
		return singleton;
	}

	public NewMember(){

		singleton = this;
		
		setStyleName("Form1");
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		final TextboxQuery TXB1 = new TextboxQuery(new Text().getText().EmployeeNumberLabel());
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().Check());
		
		VerticalPanel VP = new VerticalPanel();
		VP.setWidth("100%");
		VP.add(ErrorPanel);
		VP.add(TXB1);
		VP.add(SBM);
		VP.setCellHorizontalAlignment(SBM, ALIGN_RIGHT);
		add(VP);
		
		SBM.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SBM.Loading(true);
				ErrorPanel.setVisible(false);
				RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/public/SRV_CheckActivatedEmployeeNumber.php"));
				REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
				try {
					REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+TXB1.getText(), new RequestCallback() {
						
						@Override
						public void onResponseReceived(Request request, Response response) {
							SBM.Loading(false);
							if(response.getText().equals("OKACTIVATED")){
								FormNewMember FormNew = new FormNewMember(TXB1.getText());
								TXB1.setTextboxEnable(false);
								add(FormNew);
								SBM.setVisible(false);
							}else{
								ErrorPanel.setTextError(new Text().getText().ErrorCheckActivatedEmployee1());
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
	}
}
