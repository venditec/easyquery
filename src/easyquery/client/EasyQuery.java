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

package easyquery.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.etc.engine.BrowserExplore;
import easyquery.client.widget.display.body.Body;
import easyquery.client.widget.display.footer.Footer;
import easyquery.client.widget.display.head.Head;

public class EasyQuery implements EntryPoint {
	
	public static EasyQuery singleton;
	public Head HEAD;
	public Body BODY;
	public Footer FOOTER;
	
	public static EasyQuery getSingleton(){
		return singleton;
	}
	
	public void onModuleLoad() {
		
		singleton = this;
		
		RootPanel.get("CONTENTWIDGET").clear();

		VerticalPanel ROOT = new VerticalPanel();
		ROOT.setWidth("100%");
		ROOT.setStyleName("ContentWidget");
		
		HEAD = new Head();
		BODY = new Body();
		FOOTER = new Footer();
		
		ROOT.add(HEAD);
		
		if((BrowserExplore.Browser().split("\\|")[0].equals("Microsoft Internet Explorer") || BrowserExplore.Browser().split("\\|")[1].equals("iexplorer") || BrowserExplore.Browser().split("\\|")[2].equals("ie"))){
			HTML DANGER = new HTML("<div id=\"Infobar\"><span id=\"Sorry\">Sorry !!</span><br/>This site does not work correctly with Internet Explorer. We recommend using <a href=\"https://www.google.com/intl/en/chrome/browser/\">Chrome</a> or <a href=\"http://www.mozilla.org/en-US/firefox/\">Firefox</a>.</div>");
			ROOT.add(DANGER);
		}else{
			ROOT.add(BODY);
			ROOT.setCellHorizontalAlignment(BODY, HasAlignment.ALIGN_CENTER);
			ROOT.add(FOOTER);
			ROOT.setCellHorizontalAlignment(FOOTER, HasAlignment.ALIGN_CENTER);
		}
		
		RootPanel.get("CONTENTWIDGET").add(ROOT);
	}
}
