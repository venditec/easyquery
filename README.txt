EASYQUERY WEBAPP

1. What is EasyQuery ?
========================

EasyQuery is a voting web application.


2. The contents of /docs
======================== ...

/src directory contains files and directories about the application interface. (This interface has been developed in Google Web Toolkit).
/db directory contain the database SQL structure.
/license directory contain the content of Creative Commons Attribution-NonCommercial 3.0 Unported License.
/war directory contain the directories and files of the web application.
	- /etc/assets directory contain the images, icons and css style.
	- /etc/lib directory contain the PHP script services where the web interface gets the database content.


3. Easyquery' deployment
===========================

3.1. INTRODUCTION
     ------------

This application uses the following technologies:
PHP 5.3 or later.
JAVA 1.6 with Google Web Toolkit 2.0.3. (Visit http://developers.google.com/web-toolkit/)
CSS for Web Application Style.
SQL to manage the database content.

Database: EasyQueryDB
The database can be initialized using the files: /db/EasyQueryDB.sql
It is intended to be deployed under the MySQL 5.0.96 with phpMyAdmin 2.6.4.

The application has been tested using Eclipse IDE with Google Web Toolkit Plugin.


3.2. REQUIREMENTS
     ------------

a. Apache 2 or later

b. JDK 1.6 or later 

c. DBMS: MySQL 5.0.96 

d. SDK GWT 2.0.3 and plugin for Eclipse IDE

   (Download: http://developers.google.com/web-toolkit/download)

e. Eclipse IDE + SubEclipse (optional)


3.3. APPLICATION DEPLOYMENT
     ------------------------------------
a. Compile the Interface in GWT-compiler. 
After compilation, the GWT-compiler will create a new folder in /war/easyquery.
In this folder there are all the interface javascript files.

b. Move app files in apache web server.
Copy all content of /war folder in /htdocs/easyquery/

b. Access the application:

   http://localhost/easyquery

c. Authenticate with the user: 12345/admin.

