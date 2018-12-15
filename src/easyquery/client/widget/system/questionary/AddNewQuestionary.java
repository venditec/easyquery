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

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.etc.engine.DateQuery;
import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.form.ButtonQuery;
import easyquery.client.widget.system.form.TextAreaQuery;
import easyquery.client.widget.system.form.TextboxQuery;
import easyquery.client.widget.system.mainpanel.MainPanel;
import easyquery.client.widget.system.user.InfoUser;

public class AddNewQuestionary extends VerticalPanel {

	public AddNewQuestionary(){
		final Error ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		final TextboxQuery TXB1 = new TextboxQuery(new Text().getText().TestName());
		TXB1.setMaxLength(40);
		final TextAreaQuery TXA1 = new TextAreaQuery(new Text().getText().Description());
		final TextboxQuery TXB2 = new TextboxQuery(new Text().getText().StartDate());
		TXB2.setMaxLength(10);
		TXB2.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(new Date()));
		final TextboxQuery TXB3 = new TextboxQuery(new Text().getText().EndDate());
		TXB3.setMaxLength(10);
		TXB3.setText("dd/mm/yyyy");
		
		final ButtonQuery SBM = new ButtonQuery(new Text().getText().OptionsAdmin_41()+" "+new Text().getText().OptionsAdmin_4());
		
		add(ErrorPanel);
		add(TXB1);
		add(TXA1);
		add(TXB2);
		add(TXB3);
		add(SBM);
		setCellHorizontalAlignment(SBM, ALIGN_RIGHT);
		
		SBM.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SBM.Loading(true);
				ErrorPanel.setVisible(false);
				if(!TXB1.getText().equals("") && !TXB2.getText().equals("") && !TXB3.getText().equals("")){
					if(TXB2.getText().length()==10 && TXB3.getText().length()==10){
						if(TXB2.getText().charAt(2) == '/' && TXB2.getText().charAt(5) == '/' && TXB3.getText().charAt(2) == '/' && TXB3.getText().charAt(5) == '/'){
							if(new DateQuery(TXB3.getText()).isMoreBigThan(new DateQuery(TXB2.getText())) || new DateQuery(TXB3.getText()).isTheSame(new DateQuery(TXB2.getText()))){
								RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_AddNewQuestionary.php"));
								REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
								try {
									REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&name="+TXB1.getText()+"&description="+TXA1.getText()+"&startdate="+TXB2.getText()+"&enddate="+TXB3.getText()+"&employee="+InfoUser.getSingleton().EmployeeNumber, new RequestCallback() {
										
										@Override
										public void onResponseReceived(Request request, Response response) {
											SBM.Loading(false);
											if(response.getText().equals("OKADDNEWQUESTIONARY")){
												Window.alert(new Text().getText().SuccesAddNewQuestionary());
												MainPanel.getSingleton().Load_QuestionaryList();
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
							}else{
								ErrorPanel.setTextError(new Text().getText().ErrorIncorrectField());
								ErrorPanel.setVisible(true);
								SBM.Loading(false);
							}
						}else{
							ErrorPanel.setTextError(new Text().getText().ErrorIncorrectField());
							ErrorPanel.setVisible(true);
							SBM.Loading(false);
						}
					}else{
						ErrorPanel.setTextError(new Text().getText().ErrorIncorrectField());
						ErrorPanel.setVisible(true);
						SBM.Loading(false);
					}
				}else{
					ErrorPanel.setTextError(new Text().getText().ErrorEmptyField());
					ErrorPanel.setVisible(true);
					SBM.Loading(false);
				}
			}
		});
	}
}
