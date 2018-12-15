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

package easyquery.client.widget.system.questionary.etc;

import java.util.LinkedList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class QuestionBean extends VerticalPanel {

	public String QuestionID;
	public HTML CodeQuestion;
	public LinkedList<AnswerBean> Answers;
	
	public static QuestionBean singleton;
	
	public static QuestionBean getSingleton(){
		return singleton;
	}
	
	public QuestionBean(String[] value){
	
		singleton = this;
		
		setWidth("100%");
		
		QuestionID = value[0];
		CodeQuestion = new HTML(value[1]);
		CodeQuestion.setStyleName("QuestionStyle");
		Answers = new LinkedList<AnswerBean>();
		for(int z=2;z<value.length;z=z+2){
			final AnswerBean Answer = new AnswerBean(value[z], value[z+1]);
			Answer.Check.NO.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					for(int i=0;i<Answers.size();i++){
						if(Answers.get(i).Check.isChecked() && !Answers.get(i).AnswerID.equals(Answer.AnswerID)){
							Answers.get(i).Check.uncheck();
						}
					}
					Answer.Check.check();
				}
			});
			Answers.add(Answer);
		}

		final VerticalPanel VPANSWERS = new VerticalPanel();
		VPANSWERS.setWidth("100%");
		for(int i=0;i<Answers.size();i++){
			VPANSWERS.add(Answers.get(i));
		}
		
		add(CodeQuestion);
		add(VPANSWERS);
		
	}
	
	public String getIdSelected(){
		for(int i=0;i<Answers.size();i++){
			if(Answers.get(i).Check.isChecked()){
				return Answers.get(i).AnswerID;
			}
		}
		return "NOSELECTED";
	}
}
