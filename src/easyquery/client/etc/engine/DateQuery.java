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

package easyquery.client.etc.engine;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateQuery {
	
	public int Day;
	public int Month;
	public int Year;
	
	public DateQuery(){
		Day = Integer.parseInt(DateTimeFormat.getFormat("dd").format(new Date()));
		Month = Integer.parseInt(DateTimeFormat.getFormat("MM").format(new Date()));
		Year = Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(new Date()));
	}
	
	public DateQuery(String date){
		Day = Integer.parseInt(date.split("\\/")[0]);
		Month = Integer.parseInt(date.split("\\/")[1]);
		Year = Integer.parseInt(date.split("\\/")[2]);
	}
	
	public String toString(){
		return Day+"/"+Month+"/"+Year;
	}
	
	public boolean isMoreBigThan(DateQuery date){
		if(Year>date.Year){
			return true;
		}else if(Year<date.Year){
			return false;
		}else{
			if(Month>date.Month){
				return true;
			}else if(Month<date.Month){
				return false;
			}else{
				if(Day>date.Day){
					return true;
				}else if(Day<date.Day){
					return false;
				}else{
					return false;
				}
			}
		}
	}
	
	public boolean isTheSame(DateQuery date){
		if(Year==date.Year && Month==date.Month && Day==date.Day) return true;
		else return false;
	}

}
