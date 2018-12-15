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

package easyquery.client.widget.system.result;


import java.util.LinkedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;
import easyquery.client.widget.system.error.Error;
import easyquery.client.widget.system.list.List;
import easyquery.client.widget.system.list.ListRowTest;
import easyquery.client.widget.system.result.etc.QuestionBeanResult;

public class ListResults extends VerticalPanel {
	
	public Error ErrorPanel;
	public List ListQuestion;
	public LinkedList<QuestionBeanResult> Questions;

	public ListResults(final String IdQuestionary){
		
		setWidth("100%");
		
		ErrorPanel = new Error("");
		ErrorPanel.setVisible(false);
		add(ErrorPanel);
		
		ListQuestion = new List();
		Questions = new LinkedList<QuestionBeanResult>();
		
		add(ListQuestion);
		
		RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetQuestionsResult.php"));
		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&idquestionary="+IdQuestionary, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					ErrorPanel.setVisible(false);
					//Window.alert(response.getText());
					if(response.getText().split("\\#")[0].equals("OK")){
						
						String[] Rows = response.getText().split("\\#");
						for(int x=1;x<Rows.length;x++){
							String[] value = Rows[x].split("\\|");
							QuestionBeanResult question = new QuestionBeanResult(value);
							Questions.add(question);
						}
						
						for(int i=0;i<Questions.size();i++){
							Widget[] widget = new Widget[1];
							widget[0] = Questions.get(i);
							ListRowTest Row = new ListRowTest(widget);
							Row.setStyleName("QuestionTestStyle");
							ListQuestion.ListHP.add(Row);
						}
						
						ListQuestion.ShowList();
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
