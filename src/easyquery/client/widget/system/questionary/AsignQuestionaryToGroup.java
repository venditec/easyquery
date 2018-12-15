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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;

public class AsignQuestionaryToGroup extends VerticalPanel {

	private Error ErrorPanel;
	private ListBox LIST1;
	private ListBox LIST2;
	
	public AsignQuestionaryToGroup(final String IdQuestionary){
		
		setWidth("100%");
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		
		LIST1 = new ListBox();
		LIST1.setVisibleItemCount(20);
		LIST2 = new ListBox();
		LIST2.setVisibleItemCount(20);
		LIST1.setStyleName("ListBox");
		LIST1.setSize("100%", "400px");
		LIST2.setStyleName("ListBox");
		LIST2.setSize("100%", "400px");
		
		Label Text1 = new Label(new Text().getText().OptionsAdmin_2());
		Text1.setStyleName("TextListHeaderRow");
		Label Text2 = new Label(new Text().getText().QuestionaryGroups());
		Text2.setStyleName("TextListHeaderRow");
		
		VerticalPanel VP1 = new VerticalPanel();
		VP1.setWidth("100%");
		VerticalPanel VP2 = new VerticalPanel();
		VP2.setWidth("100%");
		
		VP1.add(Text1);
		VP1.setCellHorizontalAlignment(Text1, ALIGN_CENTER);
		VP1.add(LIST1);
		VP1.setCellHorizontalAlignment(LIST1, ALIGN_CENTER);
		
		VP2.add(Text2);
		VP2.setCellHorizontalAlignment(Text2, ALIGN_CENTER);
		VP2.add(LIST2);
		VP2.setCellHorizontalAlignment(LIST2, ALIGN_CENTER);
		
		HorizontalPanel HP = new HorizontalPanel();
		HP.setWidth("100%");
		HP.add(VP1);
		HP.setCellWidth(VP1, "45%");
		HP.add(VP2);
		HP.setCellWidth(VP2, "45%");
		
		add(ErrorPanel);
		add(HP);
		
		LoadList1(IdQuestionary);
		LoadList2(IdQuestionary);
		
		LIST1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ErrorPanel.setVisible(false);
				RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_AsignQuestionaryToGroup.php"));
				REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
				try {
					REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary+"&idgroup="+LIST1.getValue(LIST1.getSelectedIndex()), new RequestCallback() {
						
						@Override
						public void onResponseReceived(Request request, Response response) {
							if(response.getText().equals("OKASIGN")){
								LoadList1(IdQuestionary);
								LoadList2(IdQuestionary);
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
		
		LIST2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ErrorPanel.setVisible(false);
				RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_DeleteAsignQuestionaryToGroup.php"));
				REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
				try {
					REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary+"&idgroup="+LIST2.getValue(LIST2.getSelectedIndex()), new RequestCallback() {
						
						@Override
						public void onResponseReceived(Request request, Response response) {
							if(response.getText().equals("OKDELETEASIGN")){
								LoadList1(IdQuestionary);
								LoadList2(IdQuestionary);
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
	}
	
	public void LoadList1(String IdQuestionary){
		LIST1.clear();
		RequestBuilder REQ1 = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetFreeGroupsToQuestionary.php"));
		REQ1.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ1.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					//Window.alert(response.getText());
					if(response.getText().split("\\#")[0].equals("OK")){
						for(int x=1;x<response.getText().split("\\#").length;x++){
							String value = response.getText().split("\\#")[x];
							LIST1.addItem(value.split("\\|")[1],value.split("\\|")[0]);
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
	}
	
	public void LoadList2(String IdQuestionary){
		LIST2.clear();
		RequestBuilder REQ2 = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetQuestionaryGroup.php"));
		REQ2.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ2.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					//Window.alert(response.getText());
					if(response.getText().split("\\#")[0].equals("OK")){
						for(int x=1;x<response.getText().split("\\#").length;x++){
							String value = response.getText().split("\\#")[x];
							LIST2.addItem(value.split("\\|")[1],value.split("\\|")[0]);
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
	}
}
