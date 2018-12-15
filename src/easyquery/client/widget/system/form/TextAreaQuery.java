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
import com.google.gwt.user.client.ui.TextArea;

public class TextAreaQuery extends HorizontalPanel {

	private TextArea TextboxValue;
	private HorizontalPanel HP1;
	private HorizontalPanel HP2;
	private Label Text;
	
	public TextAreaQuery(String TextLabel){
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
		TextboxValue = new TextArea();
		TextboxValue.setCharacterWidth(25);
		TextboxValue.setHeight("100px");
		TextboxValue.setStyleName("TextboxValue");
		
		HP1.add(Text);
		HP2.add(TextboxValue);
		
		add(HP1);
		add(HP2);
	}
	
	public void setTextboxEnable(boolean value){
		TextboxValue.setEnabled(value);
		if(!value){
			HP1.setStyleName("TextboxQuery1_Disabled");
			HP2.setStyleName("TextboxQuery2_Disabled");
			Text.setStyleName("TextboxLabel_Disabled");
			TextboxValue.setStyleName("TextboxValue_Disabled");
		}
	}
	
	public String getText(){
		String value = TextboxValue.getText();
		value = value.replaceAll("'", "`");
		value = value.replaceAll("\\|", "\\%");
		value = value.replaceAll("\\#", "\\$");
		return value;
	}
	
	public void setText(String value){
		value = value.replaceAll("`", "'");
		value = value.replaceAll("\\%", "\\|");
		value = value.replaceAll("\\$", "\\#");
		TextboxValue.setText(value);
	}
	
	public TextArea getTextArea(){
		return TextboxValue;
	}
	
}
