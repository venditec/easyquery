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
/*
 * This is the security code to recive the request from client
 * Put the same security code of SecurityCode.java
 */ 
$SECURITYCODE = "12345";

$db = dbConect();
if($_POST["codeW"]==$SECURITYCODE){
$sql = sprintf("INSERT INTO reply (enddate,employeenumber,questionary_id) VALUES ('%s','%d','%d')",date("d/m/Y"),FSQL($_POST["employee"]),FSQL($_POST["idquestionary"]));
$query = mysql_query($sql,$db);

$idreply = mysql_insert_id();

for($i=0;$i<$_POST["numquestions"];$i++){
	$sql = sprintf("INSERT INTO result (reply_id, question_id, answer_id) VALUES ('%d','%d','%d')",$idreply,FSQL($_POST["idquestion".$i]),FSQL($_POST["answerselected".$i]));
	$query = mysql_query($sql,$db);
}
echo "OKSAVERESULTTEST";
}
mysql_close($db);

?>