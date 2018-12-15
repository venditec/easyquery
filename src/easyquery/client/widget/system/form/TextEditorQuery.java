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

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.widget.system.form.texteditor.TextEditor;

public class TextEditorQuery extends VerticalPanel {

	public TextEditor Texteditor;
	public HorizontalPanel HP1;
	public HorizontalPanel HP2;
	private Label Text;
	private String Identify;
	
	public TextEditorQuery(String TextLabel){
		setWidth("100%");
		setStyleName("TextBoxQuery");
		
		HP1 = new HorizontalPanel();
		HP1.setStyleName("TextboxQuery1");
		HP1.setWidth("100%");
		HP2 = new HorizontalPanel();
		HP2.setStyleName("TextboxQuery2");
		HP2.setWidth("100%");
		
		Text = new Label(TextLabel);
		Text.setStyleName("TextboxLabel");
		Texteditor = new TextEditor();
		Texteditor.setStyleName("TextEditor");
		
		HP1.add(Text);
		HP2.add(Texteditor);
		
		add(HP1);
		add(HP2);
	}

	public void setIdentify(String identify) {
		Identify = identify;
	}

	public String getIdentify() {
		return Identify;
	}

}
