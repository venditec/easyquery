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

package easyquery.client.widget.system.mainpanel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.etc.lang.Text;
import easyquery.client.widget.system.group.AddGroup;
import easyquery.client.widget.system.group.AsignUserToGroup;
import easyquery.client.widget.system.group.ListGroup;
import easyquery.client.widget.system.options.AdminOptions;
import easyquery.client.widget.system.options.MemberOptions;
import easyquery.client.widget.system.question.AddNewQuestion;
import easyquery.client.widget.system.question.EditQuestion;
import easyquery.client.widget.system.question.ListQuestion;
import easyquery.client.widget.system.questionary.AddNewQuestionary;
import easyquery.client.widget.system.questionary.AsignQuestionaryToGroup;
import easyquery.client.widget.system.questionary.DoMakeTest;
import easyquery.client.widget.system.questionary.EditQuestionary;
import easyquery.client.widget.system.questionary.ListQuestionary;
import easyquery.client.widget.system.questionary.ListQuestionaryForMembers;
import easyquery.client.widget.system.questionary.ListQuestionaryResult;
import easyquery.client.widget.system.questionary.ListQuestionaryResultForMembers;
import easyquery.client.widget.system.result.ListResults;
import easyquery.client.widget.system.user.AddUser;
import easyquery.client.widget.system.user.ChangePasswordForm;
import easyquery.client.widget.system.user.InfoUser;
import easyquery.client.widget.system.user.ListUser;
import easyquery.client.widget.system.user.UpdateUserForm;
import easyquery.client.widget.system.user.ViewUserForm;

public class MainPanel extends VerticalPanel {
	
	private static MainPanel singleton;

	private HorizontalPanel HP;
	public VerticalPanel MENU;
	public ScrollPanel SCROLLMAIN;
	public VerticalPanel MAIN;
	public HorizontalPanel LOGIN;
	
	public static MainPanel getSingleton() {
		return singleton;
	}
	
	public MainPanel(String EmployeeNumber, String role){
		
		singleton = this;
		
		setStyleName("Main");
		
		HP = new HorizontalPanel();
		HP.setWidth("100%");
		
		MENU = new VerticalPanel();
		MENU.setStyleName("MenuPanel");
		MENU.setWidth("100%");
		if(role.equals("A")){
			AdminOptions opAdmin = new AdminOptions();
			MENU.add(opAdmin);
			MENU.setCellHorizontalAlignment(opAdmin, ALIGN_LEFT);
			MENU.setCellVerticalAlignment(opAdmin, ALIGN_TOP);
		}else{
			MemberOptions opMember = new MemberOptions();
			MENU.add(opMember);
			MENU.setCellHorizontalAlignment(opMember, ALIGN_LEFT);
			MENU.setCellVerticalAlignment(opMember, ALIGN_TOP);
		}
		
		SCROLLMAIN = new ScrollPanel();
		SCROLLMAIN.setStyleName("scroll");
		SCROLLMAIN.setSize("100%", "500px");
		
		MAIN = new VerticalPanel();
		MAIN.setStyleName("MainPanel");
		MAIN.setWidth("100%");
		
		LOGIN = new HorizontalPanel();
		LOGIN.setStyleName("LoginPanel");
		LOGIN.setWidth("100%");
		LOGIN.add(new InfoUser(EmployeeNumber));
		
		HP.add(MENU);
		HP.setCellWidth(MENU, "24%");
		HP.setCellVerticalAlignment(MENU, ALIGN_TOP);
		HP.add(MAIN);
		HP.setCellWidth(MAIN, "76%");
		HP.setCellVerticalAlignment(MAIN, ALIGN_TOP);
		
		add(LOGIN);
		setCellVerticalAlignment(LOGIN, ALIGN_TOP);
		add(HP);
		setCellVerticalAlignment(HP, ALIGN_TOP);
		
	}
	
	private void reset(){
		MAIN.clear();
		SCROLLMAIN.clear();
	}
	
	public void Load_UpdateUserForm(String employeenumber){
		reset();
		UpdateUserForm Widget = new UpdateUserForm(employeenumber);
		Label Text = new Label(new Text().getText().OptionsAdmin_11()+" "+new Text().getText().OptionsAdmin_1());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_ChangePasswordForm(String employeenumber){
		reset();
		ChangePasswordForm Widget = new ChangePasswordForm(employeenumber);
		Label Text = new Label(new Text().getText().OptionsAdmin_12());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.add(Widget);
		MAIN.setCellHorizontalAlignment(Widget, ALIGN_CENTER);
	}
	
	public void Load_AddGroupForm(){
		reset();
		AddGroup Widget = new AddGroup();
		Label Text = new Label(new Text().getText().OptionsAdmin_21()+" "+new Text().getText().OptionsAdmin_2());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.add(Widget);
		MAIN.setCellHorizontalAlignment(Widget, ALIGN_CENTER);
	}
	
	public void Load_ListGroup(){
		reset();
		ListGroup Widget = new ListGroup();
		Label Text = new Label(new Text().getText().OptionsAdmin_22()+" "+new Text().getText().OptionsAdmin_2());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_AddUser(){
		reset();
		AddUser Widget = new AddUser();
		Label Text = new Label(new Text().getText().OptionsAdmin_31()+" "+new Text().getText().OptionsAdmin_3());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		MAIN.add(Widget);
		MAIN.setCellHorizontalAlignment(Widget, ALIGN_CENTER);
		MAIN.setCellVerticalAlignment(Widget, ALIGN_TOP);
	}
	
	public void Load_ListUser(String Group_id){
		reset();
		ListUser Widget = new ListUser(Group_id);
		Label Text = new Label(new Text().getText().OptionsAdmin_32()+" "+new Text().getText().OptionsAdmin_3());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_GroupAssign(String IDGroup, String NameGroup){
		reset();
		AsignUserToGroup Widget = new AsignUserToGroup(IDGroup,NameGroup);
		Label Text1 = new Label(new Text().getText().OptionsAdmin_23()+" "+new Text().getText().OptionsAdmin_2());
		Text1.setStyleName("MiniTitle");
		Label Text2 = new Label(NameGroup);
		Text2.setStyleName("MiniTitle1");
		MAIN.add(Text1);
		MAIN.setCellHorizontalAlignment(Text1, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text1, ALIGN_TOP);
		MAIN.add(Text2);
		MAIN.setCellHorizontalAlignment(Text2, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text2, ALIGN_TOP);
		MAIN.add(Widget);
		MAIN.setCellHorizontalAlignment(Widget, ALIGN_CENTER);
		MAIN.setCellVerticalAlignment(Widget, ALIGN_TOP);
	}
	
	public void Load_AddNewQuestionary(){
		reset();
		AddNewQuestionary Widget = new AddNewQuestionary();
		Label Text = new Label(new Text().getText().OptionsAdmin_41()+" "+new Text().getText().OptionsAdmin_4());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		MAIN.add(Widget);
		MAIN.setCellHorizontalAlignment(Widget, ALIGN_CENTER);
		MAIN.setCellVerticalAlignment(Widget, ALIGN_TOP);
	}
	
	public void Load_QuestionaryList(){
		reset();
		ListQuestionary Widget = new ListQuestionary();
		Label Text = new Label(new Text().getText().OptionsAdmin_42()+" "+new Text().getText().OptionsAdmin_4());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_QuestionaryAssign(String IdQuestionary, String NameQuestionary){
		reset();
		AsignQuestionaryToGroup Widget = new AsignQuestionaryToGroup(IdQuestionary);
		Label Text1 = new Label(new Text().getText().OptionsAdmin_23()+" "+new Text().getText().OptionsAdmin_2());
		Text1.setStyleName("MiniTitle");
		Label Text2 = new Label(NameQuestionary);
		Text2.setStyleName("MiniTitle1");
		MAIN.add(Text1);
		MAIN.setCellHorizontalAlignment(Text1, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text1, ALIGN_TOP);
		MAIN.add(Text2);
		MAIN.setCellHorizontalAlignment(Text2, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text2, ALIGN_TOP);
		MAIN.add(Widget);
		MAIN.setCellHorizontalAlignment(Widget, ALIGN_CENTER);
		MAIN.setCellVerticalAlignment(Widget, ALIGN_TOP);
	}
	
	public void Load_EditQuestionary(String IdQuestionary){
		reset();
		EditQuestionary Widget = new EditQuestionary(IdQuestionary);
		Label Text = new Label(new Text().getText().OptionsAdmin_43()+" "+new Text().getText().OptionsAdmin_4());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		MAIN.add(Widget);
		MAIN.setCellHorizontalAlignment(Widget, ALIGN_CENTER);
		MAIN.setCellVerticalAlignment(Widget, ALIGN_TOP);
	}
	
	public void Load_AddNewQuestion(){
		reset();
		AddNewQuestion Widget = new AddNewQuestion();
		Label Text = new Label(new Text().getText().OptionsAdmin_51()+" "+new Text().getText().OptionsAdmin_5());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_ListQuestion(String IdQuestionary){
		reset();
		ListQuestion Widget = new ListQuestion(IdQuestionary);
		Label Text = new Label(new Text().getText().OptionsAdmin_52()+" "+new Text().getText().OptionsAdmin_5());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_EditQuestion(String IdQuestion, String IdQuestionary){
		reset();
		EditQuestion Widget = new EditQuestion(IdQuestion, IdQuestionary);
		Label Text = new Label(new Text().getText().OptionsAdmin_53()+" "+new Text().getText().OptionsAdmin_5());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_QuestionaryListMember(){
		reset();
		ListQuestionaryForMembers Widget = new ListQuestionaryForMembers();
		Label Text = new Label(new Text().getText().OptionsMember_21()+" "+new Text().getText().OptionsMember_2());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_DoMakeTest(String Employee, String IdQuestionary, String NameTest){
		reset();
		DoMakeTest Widget = new DoMakeTest(Employee,IdQuestionary);
		Label Text = new Label(NameTest);
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_ListResult(String IdQuestionary, String NameTest){
		reset();
		ListResults Widget = new ListResults(IdQuestionary);
		Label Text = new Label(NameTest);
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_QuestionaryResult(){
		reset();
		ListQuestionaryResult Widget = new ListQuestionaryResult();
		Label Text = new Label(new Text().getText().OptionsAdmin_61()+" "+new Text().getText().OptionsAdmin_6());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_QuestionaryResultForMembers(){
		reset();
		ListQuestionaryResultForMembers Widget = new ListQuestionaryResultForMembers();
		Label Text = new Label(new Text().getText().OptionsMember_22()+" "+new Text().getText().OptionsMember_2());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
	
	public void Load_ViewUserForm(String Employee){
		reset();
		ViewUserForm Widget = new ViewUserForm(Employee);
		Label Text = new Label(new Text().getText().UserDetails());
		Text.setStyleName("MiniTitle");
		MAIN.add(Text);
		MAIN.setCellHorizontalAlignment(Text, ALIGN_RIGHT);
		MAIN.setCellVerticalAlignment(Text, ALIGN_TOP);
		SCROLLMAIN.add(Widget);
		MAIN.add(SCROLLMAIN);
	}
}
