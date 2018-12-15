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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.etc.engine.Redirect;
import easyquery.client.etc.lang.Text;
import easyquery.client.widget.system.mainpanel.MainPanel;
import easyquery.client.widget.system.treepanel.TreeElement;
import easyquery.client.widget.system.treepanel.TreePanel;
import easyquery.client.widget.system.user.InfoUser;

public class AdminOptions extends VerticalPanel {

	public AdminOptions(){
		
		setWidth("100%");
		
		TreeElement e = new TreeElement();
		Label el = new Label(new Text().getText().OptionsAdmin());
		e.setName(el);
		
			TreeElement e1 = new TreeElement();
			Label el1 = new Label(new Text().getText().OptionsAdmin_1());
			e1.setName(el1);
			
				TreeElement e11 = new TreeElement();
				Label el11 = new Label(new Text().getText().OptionsAdmin_11());
				el11.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_UpdateUserForm(InfoUser.getSingleton().EmployeeNumber);
					}
				});
				e11.setName(el11);
				TreeElement e12 = new TreeElement();
				Label el12 = new Label(new Text().getText().OptionsAdmin_12());
				el12.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_ChangePasswordForm(InfoUser.getSingleton().EmployeeNumber);
					}
				});
				e12.setName(el12);
				
			TreeElement e2 = new TreeElement();
			Label el2 = new Label(new Text().getText().OptionsAdmin_2());
			e2.setName(el2);
				
				TreeElement e21 = new TreeElement();
				Label el21 = new Label(new Text().getText().OptionsAdmin_21());
				el21.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_AddGroupForm();
					}
				});
				e21.setName(el21);
				TreeElement e22 = new TreeElement();
				Label el22 = new Label(new Text().getText().OptionsAdmin_22());
				el22.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_ListGroup();
					}
				});
				e22.setName(el22);
			
			TreeElement e3 = new TreeElement();
			Label el3 = new Label(new Text().getText().OptionsAdmin_3());
			e3.setName(el3);
					
				TreeElement e31 = new TreeElement();
				Label el31 = new Label(new Text().getText().OptionsAdmin_31());
				el31.addClickHandler(new ClickHandler() {
						
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_AddUser();
							
					}
				});
				e31.setName(el31);
				TreeElement e32 = new TreeElement();
				Label el32 = new Label(new Text().getText().OptionsAdmin_32());
				el32.addClickHandler(new ClickHandler() {
						
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_ListUser("");
					}
				});
				e32.setName(el32);
				
			TreeElement e4 = new TreeElement();
			Label el4 = new Label(new Text().getText().OptionsAdmin_4());
			e4.setName(el4);
					
				TreeElement e41 = new TreeElement();
				Label el41 = new Label(new Text().getText().OptionsAdmin_41());
				el41.addClickHandler(new ClickHandler() {
						
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_AddNewQuestionary();
					}
				});
				e41.setName(el41);
				TreeElement e42 = new TreeElement();
				Label el42 = new Label(new Text().getText().OptionsAdmin_42());
				el42.addClickHandler(new ClickHandler() {
						
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_QuestionaryList();	
					}
				});
				e42.setName(el42);
				
			TreeElement e5 = new TreeElement();
			Label el5 = new Label(new Text().getText().OptionsAdmin_5());
			e5.setName(el5);
					
				TreeElement e51 = new TreeElement();
				Label el51 = new Label(new Text().getText().OptionsAdmin_51());
				el51.addClickHandler(new ClickHandler() {
						
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_AddNewQuestion();
					}
				});
				e51.setName(el51);
				TreeElement e52 = new TreeElement();
				Label el52 = new Label(new Text().getText().OptionsAdmin_52());
				el52.addClickHandler(new ClickHandler() {
						
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_ListQuestion("");	
					}
				});
				e52.setName(el52);
				
			TreeElement e6 = new TreeElement();
			Label el6 = new Label(new Text().getText().OptionsAdmin_6());
			e6.setName(el6);
						
				TreeElement e61 = new TreeElement();
				Label el61 = new Label(new Text().getText().OptionsAdmin_61());
				el61.addClickHandler(new ClickHandler() {
							
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_QuestionaryResult();		
					}
				});
				e61.setName(el61);
		
		
		e11.setSonElements(new LinkedList<TreeElement>());
		e12.setSonElements(new LinkedList<TreeElement>());
		e21.setSonElements(new LinkedList<TreeElement>());
		e22.setSonElements(new LinkedList<TreeElement>());
		e31.setSonElements(new LinkedList<TreeElement>());
		e32.setSonElements(new LinkedList<TreeElement>());
		e41.setSonElements(new LinkedList<TreeElement>());
		e42.setSonElements(new LinkedList<TreeElement>());
		e51.setSonElements(new LinkedList<TreeElement>());
		e52.setSonElements(new LinkedList<TreeElement>());
		e61.setSonElements(new LinkedList<TreeElement>());
		
		LinkedList<TreeElement> le6 = new LinkedList<TreeElement>();
		le6.add(e61);
		e6.setSonElements(le6);
		
		LinkedList<TreeElement> le5 = new LinkedList<TreeElement>();
		le5.add(e51);
		le5.add(e52);
		e5.setSonElements(le5);
		
		LinkedList<TreeElement> le4 = new LinkedList<TreeElement>();
		le4.add(e41);
		le4.add(e42);
		e4.setSonElements(le4);
		
		LinkedList<TreeElement> le3 = new LinkedList<TreeElement>();
		le3.add(e31);
		le3.add(e32);
		e3.setSonElements(le3);
		
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
		le.add(e3);
		le.add(e4);
		le.add(e5);
		le.add(e6);
		e.setSonElements(le);
		
		TreePanel tp = new TreePanel();
		tp.showTreePanel(e,0);
		add(tp);
		
		e.setOpen(true);
		e.setIcon(e.getIconOpen());
		for(int i=0;i<e.getSonElements().size();i++){
			e.Open(e.getSonElements().get(i));
		}
		
		// ------------------- MEMEBER OPTIONS -----------------
		
		TreeElement m = new TreeElement();
		Label ml = new Label(new Text().getText().OptionsMember());
		m.setName(ml);
				
			TreeElement m1 = new TreeElement();
			Label ml1 = new Label(new Text().getText().OptionsMember_2());
			m1.setName(ml1);
				
				TreeElement m11 = new TreeElement();
				Label ml11 = new Label(new Text().getText().OptionsMember_21());
				ml11.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_QuestionaryListMember();
					}
				});
				m11.setName(ml11);
				TreeElement m12 = new TreeElement();
				Label ml12 = new Label(new Text().getText().OptionsMember_22());
				ml12.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						MainPanel.getSingleton().Load_QuestionaryResultForMembers();
						
					}
				});
				m12.setName(ml12);
				
				TreeElement m2 = new TreeElement();
				Label ml2 = new Label(new Text().getText().OptionsMember_7());
				ml2.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						Redirect.setLocation(GWT.getHostPageBaseURL());
					}
				});
				m2.setName(ml2);
				
				m11.setSonElements(new LinkedList<TreeElement>());
				m12.setSonElements(new LinkedList<TreeElement>());
				m2.setSonElements(new LinkedList<TreeElement>());
				
				LinkedList<TreeElement> lm1 = new LinkedList<TreeElement>();
				lm1.add(m11);
				lm1.add(m12);
				m1.setSonElements(lm1);
				
				LinkedList<TreeElement> lm = new LinkedList<TreeElement>();
				lm.add(m1);
				lm.add(m2);
				m.setSonElements(lm);
				
				TreePanel tp1 = new TreePanel();
				tp1.showTreePanel(m,0);
				add(tp1);
				
				m.setOpen(true);
				m.setIcon(m.getIconOpen());
				for(int i=0;i<m.getSonElements().size();i++){
					m.Open(m.getSonElements().get(i));
				}
	}
}
