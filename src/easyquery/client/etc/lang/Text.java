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

import com.google.gwt.core.client.GWT;

public class Text {
    
    private Language Text;
    
    /**
     * Class contructor
     */
    public Text(){
    	Text = GWT.create(Language.class);
    }
    
    /**
     * @return a constant
     */
    public Language getText(){
        return Text;
    }
}
