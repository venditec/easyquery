<?php
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
function dateIsMajor($date1,$date2){
	$day1 = $date1[0].$date1[1];
	$month1 = $date1[3].$date1[4];
	$year1 = $date1[6].$date1[7].$date1[8].$date1[9];
	
	$day2 = $date2[0].$date2[1];
	$month2 = $date2[3].$date2[4];
	$year2 = $date2[6].$date2[7].$date2[8].$date2[9];
	
	if($year1>$year2){
			return true;
		}else if($year1<$year2){
			return false;
		}else{
			if($month1>$month2){
				return true;
			}else if($month1<$month2){
				return false;
			}else{
				if($day1>$day2){
					return true;
				}else if($day1<$day2){
					return false;
				}else{
					return false;
				}
			}
		}
}

function dateIsTheSame($date1,$date2){
	$day1 = $date1[0].$date1[1];
	$month1 = $date1[3].$date1[4];
	$year1 = $date1[6].$date1[7].$date1[8].$date1[9];
	
	$day2 = $date2[0].$date2[1];
	$month2 = $date2[3].$date2[4];
	$year2 = $date2[6].$date2[7].$date2[8].$date2[9];
	
	if($day1==$day2 && $month1==$month2 && $year1==$year2) return true;
	else false;
}

?>