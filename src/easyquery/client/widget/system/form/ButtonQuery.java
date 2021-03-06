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

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

import easyquery.client.etc.icon.Icon;

public class ButtonQuery extends HorizontalPanel {

	private Button BOTO;
	private Image LOAD;
	
	public ButtonQuery(String Text){
		
		setStyleName("ButtonSubmit");
		
		BOTO = new Button();
		LOAD = new Image();
		LOAD.setVisible(false);
		
		BOTO.setText(Text);
		BOTO.setStyleName("Button");
		
		LOAD.setUrl(new Icon().Loading);
		
		add(LOAD);
		setCellVerticalAlignment(LOAD, ALIGN_MIDDLE);
		add(BOTO);
		
		BOTO.addMouseOverHandler(new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				setStyleName("ButtonSubmitHover");
				BOTO.setStyleName("ButtonHover");
				LOAD.setUrl(new Icon().LoadingActivated);
			}
		});
		
		BOTO.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				setStyleName("ButtonSubmit");
				BOTO.setStyleName("Button");
				LOAD.setUrl(new Icon().Loading);
			}
		});
	}
	
	public void Loading(boolean value){
		LOAD.setVisible(value);
	}
	
	public Button getButton(){
		return BOTO;
	}
	
}
