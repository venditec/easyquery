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

package easyquery.client.widget.system.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import easyquery.client.etc.security.SecurityCode;


public class ListBoxQuery extends HorizontalPanel {

	public ListBox List;
	private HorizontalPanel HP1;
	private HorizontalPanel HP2;
	private Label Text;
	
	public ListBoxQuery(String TextLabel){
		
		setWidth("100%");
		setStyleName("TextBoxQuery");
		
		HP1 = new HorizontalPanel();
		HP1.setStyleName("TextboxQuery1");
		HP1.setWidth("100%");
		HP2 = new HorizontalPanel();
		HP2.setStyleName("TextboxQuery2List");
		HP2.setWidth("100%");
		
		Text = new Label(TextLabel);
		Text.setStyleName("TextboxLabel");
		List = new ListBox();
		List.setStyleName("ListBox");
		
		HP1.add(Text);
		HP2.add(List);
		add(HP1);
		add(HP2);
		
	}
	
	public ListBoxQuery(String TextLabel, String URLLoadData){
		
		setWidth("100%");
		setStyleName("TextBoxQuery");
		
		HP1 = new HorizontalPanel();
		HP1.setStyleName("TextboxQuery1");
		HP1.setWidth("100%");
		HP2 = new HorizontalPanel();
		HP2.setStyleName("TextboxQuery2List");
		HP2.setWidth("100%");
		
		Text = new Label(TextLabel);
		Text.setStyleName("TextboxLabel");
		List = new ListBox();
		List.setStyleName("ListBox");
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + URLLoadData));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode(), new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					for(int i=0;i<response.getText().split("\\#").length;i++){
						List.addItem(response.getText().split("\\#")[i].split("\\|")[0], response.getText().split("\\#")[i].split("\\|")[1]);
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
		HP1.add(Text);
		HP2.add(List);
		add(HP1);
		add(HP2);
	}
}
