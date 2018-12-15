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

package easyquery.client.widget.system.options;

import java.util.LinkedList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import easyquery.client.EasyQuery;
import easyquery.client.etc.lang.Text;
import easyquery.client.widget.system.mainpanel.MainPanel;
import easyquery.client.widget.system.treepanel.TreeElement;
import easyquery.client.widget.system.treepanel.TreePanel;
import easyquery.client.widget.system.user.InfoUser;

public class MemberOptions extends HorizontalPanel {

	public MemberOptions(){
		
		setWidth("100%");
		
		TreeElement e = new TreeElement();
		Label el = new Label(new Text().getText().OptionsMember());
		e.setName(el);
		
			TreeElement e1 = new TreeElement();
			Label el1 = new Label(new Text().getText().OptionsMember_1());
			e1.setName(el1);
			
				TreeElement e11 = new TreeElement();
				Label el11 = new Label(new Text().getText().OptionsMember_11());
				el11.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_UpdateUserForm(InfoUser.getSingleton().EmployeeNumber);
					}
				});
				e11.setName(el11);
				TreeElement e12 = new TreeElement();
				Label el12 = new Label(new Text().getText().OptionsMember_12());
				el12.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_ChangePasswordForm(InfoUser.getSingleton().EmployeeNumber);
					}
				});
				e12.setName(el12);
				
			TreeElement e2 = new TreeElement();
			Label el2 = new Label(new Text().getText().OptionsMember_2());
			e2.setName(el2);
				
				TreeElement e21 = new TreeElement();
				Label el21 = new Label(new Text().getText().OptionsMember_21());
				el21.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_QuestionaryListMember();
					}
				});
				e21.setName(el21);
				TreeElement e22 = new TreeElement();
				Label el22 = new Label(new Text().getText().OptionsMember_22());
				el22.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_QuestionaryResultForMembers();
					}
				});
				e22.setName(el22);
				
				TreeElement e7 = new TreeElement();
				Label el7 = new Label(new Text().getText().OptionsMember_7());
				e7.setName(el7);
				el7.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						EasyQuery.getSingleton().onModuleLoad();
					}
				});
				
				e11.setSonElements(new LinkedList<TreeElement>());
				e12.setSonElements(new LinkedList<TreeElement>());
				e21.setSonElements(new LinkedList<TreeElement>());
				e22.setSonElements(new LinkedList<TreeElement>());
				e7.setSonElements(new LinkedList<TreeElement>());
				
				LinkedList<TreeElement> le2 = new LinkedList<TreeElement>();
				le2.add(e21);
				le2.add(e22);
				e2.setSonElements(le2);
				
				LinkedList<TreeElement> le1 = new LinkedList<TreeElement>();
				le1.add(e11);
				le1.add(e12);
				e1.setSonElements(le1);
				
				LinkedList<TreeElement> le = new LinkedList<TreeElement>();
				le.add(e1);
				le.add(e2);
				le.add(e7);
				e.setSonElements(le);
				
				TreePanel tp = new TreePanel();
				tp.showTreePanel(e,0);
				add(tp);
				
				e.setOpen(true);
				e.setIcon(e.getIconOpen());
				for(int i=0;i<e.getSonElements().size();i++){
					e.Open(e.getSonElements().get(i));
				}
		
	}
}
