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
include("../_etc/engine/SendMail.php");
include("../_etc/engine/Random.php");
include("../_etc/mail/class.phpmailer.php");
/*
 * This is the security code to recive the request from client
 * Put the same security code of SecurityCode.java
 */ 
$SECURITYCODE = "12345";

$db = dbConect();
if($_POST["codeW"]==$SECURITYCODE){
if(!empty($_POST["mail"])){
	$sql = sprintf("SELECT COUNT(*) AS 'num' FROM user WHERE mail='%s'",FSQL($_POST["mail"]));
	$query = mysql_query($sql,$db);
	$ob = mysql_fetch_object($query);
	if($ob->num == 1){
		$newpassword = Random(6);
		$sql = sprintf("SELECT u.*,m.* FROM user u, member m WHERE u.employeenumber=m.employeenumber and u.mail='%s'",FSQL($_POST["mail"]));
		$query = mysql_query($sql,$db);
		$ob = mysql_fetch_object($query);
		$M_name = $ob->name;
		$M_surname = $ob->surname;
		$M_addressmail = $ob->mail;
		
		$sql = sprintf("UPDATE user SET employeepassword = '%s' WHERE employeenumber = '%d'",sha1($newpassword),$ob->employeenumber);
		$query = mysql_query($sql,$db);
		
		$M_subject = "- EZY Union - New Password";
		$M_body = "Hello ".$M_name." ".$M_surname."\n\nYour new password is: ".$newpassword."\n\nPasswords can be changed in 'My account' option that you will find in the main menu.\n\nBest regards,\n\nEZY Union team";
		if(SendMail($M_name,$M_surname,$M_addressmail,$M_subject,$M_body)){
			echo "REMEMBEROK";
		}else{
			echo "SERVERERROR";
		}
	}else{
		echo "MAILUNKNOWN";
	}
}else{
	echo "EMPTYFIELD";
}
}
mysql_close($db);

?>