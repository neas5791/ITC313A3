CREATE DATABASE IF NOT EXISTS ST11187033;
USE ST11187033;
DROP TABLE IF EXISTS Student_marks_ITC000;
CREATE TABLE Student_marks_ITC000(
		StudentID	integer AUTO_INCREMENT unique,
		Name		varchar(50),
		Assignment1	integer,
		Assignment2	integer,
		Assignment3	integer,
		Final		integer,
	PRIMARY KEY (StudentID)
)ENGINE=InnoDB AUTO_INCREMENT=1001;/* CREATES THE STUDENT TABLE AND SETS PK TO START AT 1000 */
INSERT INTO Student_marks_ITC000 (Name, Assignment1, Assignment2, Assignment3, Final) values ('Janet', 80,100,90,85);/* ADDS RECORD TO TABLE */
INSERT INTO Student_marks_ITC000 (Name, Assignment1, Assignment2, Assignment3, Final) values ('Daniel', 60,70,75,90);/* ADDS RECORD TO TABLE */