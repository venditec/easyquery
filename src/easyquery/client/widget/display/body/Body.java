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

package easyquery.client.widget.display.body;

import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.widget.system.auth.Auth;
import easyquery.client.widget.system.mainpanel.MainPanel;
import easyquery.client.widget.system.newmember.NewMember;
import easyquery.client.widget.system.remember.Remember;

public class Body extends VerticalPanel {

	private static Body singleton;
	
	public static Body getSingleton(){
		return singleton;
	}
	
	public Body(){
		singleton = this;
		LoadAuth();
	}
	
	public void reset(){
		clear();
		setWidth("100%");
	}
	
	public void LoadAuth(){
		reset();
		Auth auth = new Auth();
		add(auth);
		setCellHorizontalAlignment(auth, ALIGN_CENTER);
		setCellVerticalAlignment(auth, ALIGN_TOP);
	}
	
	public void LoadFormRemember(){
		reset();
		Remember FormRememeber = new Remember();
		add(FormRememeber);
		setCellHorizontalAlignment(FormRememeber, ALIGN_CENTER);
		setCellVerticalAlignment(FormRememeber, ALIGN_TOP);
	}
	
	public void LoadFormNewMember(){
		reset();
		NewMember FormNewMember = new NewMember();
		add(FormNewMember);
		setCellHorizontalAlignment(FormNewMember, ALIGN_CENTER);
		setCellVerticalAlignment(FormNewMember, ALIGN_TOP);
	}
	
	public void LoadPanel(String EmployeeNumber, String role){
		reset();
		MainPanel Panel = new MainPanel(EmployeeNumber, role);
		add(Panel);
		setCellHorizontalAlignment(Panel, ALIGN_CENTER);
		setCellVerticalAlignment(Panel, ALIGN_TOP);
	}
}
