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

package easyquery.client.etc.lang;

import com.google.gwt.i18n.client.Constants;

public interface Language extends Constants{
	
	String Title();
	String EmployeeNumberLabel();
	String EmployeePasswordLabel();
	String EmployeeOldPasswordLabel();
	String EmployeeNewPasswordLabel();
	String EmployeeReNewPasswordLabel();
	String ShowTextBoxPassword();
	String AuthButton();
	String RememberPasswordLabel();
	String CreateAccountLabel();
	String RegisterLabel();
	String Copyright();
	String LegalNotice();
	String Check();
	String Name();
	String Surname();
	String Mail();
	String PrivateMail();
	String Address();
	String City();
	String PostalCode();
	String Country();
	String Phone();
	String Create();
	String Update();
	String ChangePassword();
	String GroupName();
	String RememberAccount();
	String Remember();
	String AddGroup();
	String UserToBeAdmin();
	String EmployeesAtGroup();
	String TestName();
	String Description();
	String StartDate();
	String EndDate();
	String Questionary();
	String QuestionaryGroups();
	String Question();
	String AnswerNumber();
	String Answer();
	String SendTest();
	String UserDetails();
	
	String ErrorAuth1();
	String ErrorAuth2();
	String ErrorCheckActivatedEmployee1();
	String ErrorNoNumericField();
	String ErrorEmptyField();
	String ErrorIncorrectField();
	String ErrorServer();
	String ErrorMailUnknown();
	String ErrorEqualsPasswords();
	String ErrorPassword();
	String ErrorMail();
	String ErrorGroupExist();
	String ErrorUserExist();
	String ErrorUsersInGroup();
	String ErrorNoAnswers();
	String ErrorQuestionarySelected();
	String ErrorQuestionaryPublic();
	
	String SuccesRememberPassword();
	String SuccesNewMember();
	String SuccesUpdateUser();
	String SuccesRegisterUser();
	String SuccesAddNewQuestionary();
	String SuccesUpdateQuestionary();
	String SuccesAddQuestion();
	String SuccesUpdateQuestion();
	String SuccesSendTest();
	
	String OptionsAdmin();
	String OptionsAdmin_1();
	String OptionsAdmin_11();
	String OptionsAdmin_12();	
	String OptionsAdmin_2();
	String OptionsAdmin_21();
	String OptionsAdmin_22();
	String OptionsAdmin_23();
	String OptionsAdmin_3();
	String OptionsAdmin_31();
	String OptionsAdmin_32();
	String OptionsAdmin_4();
	String OptionsAdmin_41();
	String OptionsAdmin_42();
	String OptionsAdmin_43();
	String OptionsAdmin_5();
	String OptionsAdmin_51();
	String OptionsAdmin_52();
	String OptionsAdmin_53();
	String OptionsAdmin_6();
	String OptionsAdmin_61();
	String OptionsAdmin_62();
	String OptionsAdmin_7();
	String OptionsMember();
	String OptionsMember_1();
	String OptionsMember_11();
	String OptionsMember_12();	
	String OptionsMember_2();
	String OptionsMember_21();
	String OptionsMember_22();
	String OptionsMember_7();
	
	String List_EmployeeNumber();
	String List_Options();
	String List_Name();
	String List_DateIni();
	String List_DateEnd();
	String List_Description();
	String List_Question();
	
	String Action_Delete();
	String Action_SendTest();
	String Action_LockUser();
	String Action_UnLockUser();
	String Action_AdminLock();
	String Action_DeleteObject();
	String Action_EditObject();
	String Action_LockObject();
	String Action_UnLockObject();
	String Action_AsignGroup();
	String Action_MakeTest();
	String Action_ViewResults();
	String Action_ViewAnswers();
}

