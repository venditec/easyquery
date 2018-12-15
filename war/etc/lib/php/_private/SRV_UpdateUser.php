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
if(!empty($_POST["employee"]) && !empty($_POST["name"]) && !empty($_POST["surname"]) && !empty($_POST["mail"]) && !empty($_POST["address"]) && !empty($_POST["city"]) && !empty($_POST["postalcode"]) && !empty($_POST["country"]) && !empty($_POST["privatemail"]) && !empty($_POST["phone"])){
	$sql = sprintf("SELECT COUNT(employeenumber) AS 'num' FROM user WHERE mail='%s' AND NOT employeenumber='%d'",FSQL($_POST["mail"]),FSQL($_POST["employee"]));
	$query = mysql_query($sql,$db);
	$ob0 = mysql_fetch_object($query);
	if($ob0->num<=0){
		$sql = sprintf("UPDATE user SET mail = '%s' WHERE employeenumber = '%d'",FSQL($_POST["mail"]),FSQL($_POST["employee"]));
		$query = mysql_query($sql,$db);
		$sql = sprintf("UPDATE member SET name = '%s', surname = '%s', address = '%s', city = '%s', postalcode = '%d', country = '%s', privatemail = '%s', phone = '%d' WHERE employeenumber = '%d'",FSQL($_POST["name"]),FSQL($_POST["surname"]),FSQL($_POST["address"]),FSQL($_POST["city"]),FSQL($_POST["postalcode"]),FSQL($_POST["country"]),FSQL($_POST["privatemail"]),FSQL($_POST["phone"]),FSQL($_POST["employee"]));
		$query = mysql_query($sql,$db);
		
		echo "OKUPDATE";
	}else{
		
	}
}else{
	echo "ERRORFIELDS";
}
}
mysql_close($db);

?>