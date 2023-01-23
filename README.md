# ZenMarker
Android app where lecturers can create assignments. Students can create groups or join existing groups for the assignent

Uses OkHttp to send and recive data, from and to the database on the server using the PHP files in the DataPHP folder which should be on the server.

App makes use of a splash screen


==============================================================================================================================================================
Database structure in MySQL: 


Create table LECTURER(LECT_ID int NOT NULL AUTO_INCREMENT, LECT_FNAME
varchar(40), LECT_LNAME varchar(30), LECT_EMAIL varchar(30),
LECT_PASSWORD varchar(30), PRIMARY KEY(LECT_ID));
mysql> DESC LECTURER;
+---------------+-------------+------+-----+---------+----------------+
| Field | Type | Null | Key | Default | Extra |
+---------------+-------------+------+-----+---------+----------------+
| LECT_ID | int(11) | NO | PRI | NULL | auto_increment |
| LECT_FNAME | varchar(40) | YES | | NULL | |
| LECT_LNAME | varchar(30) | YES | | NULL | |
| LECT_EMAIL | varchar(30) | YES | | NULL | |
| LECT_PASSWORD | varchar(30) | YES | | NULL | |
+---------------+-------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)
Create table STUDENT(STU_ID int NOT NULL AUTO_INCREMENT, STU_FNAME
varchar(40), STU_LNAME varchar(30), STU_EMAIL varchar(30), STU_PASSWORD
varchar(30), PRIMARY KEY(LECT_ID));
mysql> desc STUDENT;
+--------------+-------------+------+-----+---------+----------------+
| Field | Type | Null | Key | Default | Extra |
+--------------+-------------+------+-----+---------+----------------+
| STU_ID | int(11) | NO | PRI | NULL | auto_increment |
| STU_FNAME | varchar(40) | YES | | NULL | |
| STU_LNAME | varchar(30) | YES | | NULL | |
| STU_EMAIL | varchar(30) | YES | | NULL | |
| STU_PASSWORD | varchar(30) | YES | | NULL | |
+--------------+-------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)
Create table ASSIGNMENT(ASS_ID int NOT NULL AUTO_INCREMENT, ASS_NAME
varchar(30), ASS_DESCRIPTION varchar(40), ASS_COURSE varchar(10),
LECT_ID int, PRIMARY KEY (ASS_ID), FOREIGN KEY (LECT_ID) REFERENCES
LECTURER(LECT_ID));
mysql> DESC ASSIGNMENT;
+-----------------+-------------+------+-----+---------+----------------+
| Field | Type | Null | Key | Default | Extra |
+-----------------+-------------+------+-----+---------+----------------+
| ASS_ID | int(11) | NO | PRI | NULL | auto_increment |
| ASS_NAME | varchar(30) | YES | | NULL | |
| ASS_DESCRIPTION | varchar(40) | YES | | NULL | |
| ASS_COURSE | varchar(10) | YES | | NULL | |
| LECT_ID | int(11) | YES | MUL | NULL | |
+-----------------+-------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)
Create table GROUPS(GROUP_ID int NOT NULL AUTO_INCREMENT, GROUP_NAME
varchar(30), GROUP_DESCRIPTION varchar(40), GROUP_MARK int, ASS_ID int,
PRIMARY KEY (GROUP_ID), FOREIGN KEY (ASS_ID) REFERENCES
ASSIGNMENT(ASS_ID));
mysql> DESC GROUPS;
+-------------------+-------------+------+-----+---------+----------------+
| Field | Type | Null | Key | Default | Extra |
+-------------------+-------------+------+-----+---------+----------------+
| GROUP_ID | int(11) | NO | PRI | NULL | auto_increment |
| GROUP_NAME | varchar(30) | YES | | NULL | |
| GROUP_DESCRIPTION | varchar(40) | YES | | NULL | |
| GROUP_MARK | int(11) | YES | | NULL | |
| ASS_ID | int(11) | YES | MUL | NULL | |
+-------------------+-------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)
Create table STUGROUPS(STU_ID int, GROUP_ID int, PRIMARY KEY(STU_ID,
GROUP_ID), FOREIGN KEY(STU_ID) REFERENCES STUDENT(STU_ID), FOREIGN KEY
(GROUP_ID) REFERENCES GROUPS(GROUP_ID));
mysql> DESC STUGROUPS;
+----------+---------+------+-----+---------+-------+
| Field | Type | Null | Key | Default | Extra |
+----------+---------+------+-----+---------+-------+
| STU_ID | int(11) | NO | PRI | 0 | |
| GROUP_ID | int(11) | NO | PRI | 0 | |
+----------+---------+------+-----+---------+-------+
2 rows in set (0.00 sec)
