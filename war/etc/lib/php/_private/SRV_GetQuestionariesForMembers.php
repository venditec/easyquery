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


include("../_etc/db/dbConect.php");
include("../_etc/engine/CompareDates.php");
/*
 * This is the security code to recive the request from client
 * Put the same security code of SecurityCode.java
 */ 
$SECURITYCODE = "12345";

$db = dbConect();
if($_POST["codeW"]==$SECURITYCODE){
$result = "OK#";
$result = $result.date("d/m/Y")."#";
$sql = sprintf("SELECT q.* FROM questionary q, assign a, user u WHERE u.employeenumber='%d' AND u.group_id=a.group_id AND a.questionary_id=q.id AND q.locked = 0 ORDER BY q.id",FSQL($_POST["employee"]));
$query = mysql_query($sql,$db);
while($ob = mysql_fetch_object($query)){
	$sql1 = sprintf("SELECT COUNT(id) AS 'num' FROM reply WHERE questionary_id='%d' AND employeenumber='%d'",$ob->id,FSQL($_POST["employee"]));
	$query1 = mysql_query($sql1,$db);
	$ob1 = mysql_fetch_object($query1);
	if(!dateIsMajor(date("d/m/Y"),$ob->enddate) && $ob1->num<=0){
		$result = $result.$ob->id."|".$ob->name."|".$ob->startdate."|".$ob->enddate."|".$ob->description."|".$ob->locked."#";
	}
}

if(strlen($result)>3)
$result[strlen($result)-1] = "";

echo $result;
}	
mysql_close($db);

?>