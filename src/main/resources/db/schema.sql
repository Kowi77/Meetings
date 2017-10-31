


DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS meets;
DROP TABLE IF EXISTS employers;
DROP TABLE IF EXISTS departs;

CREATE TABLE departs
(
  id    INTEGER PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(60) NOT NULL
);

CREATE TABLE employers
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  fullname   VARCHAR(60) NOT NULL,
  birthday   TIMESTAMP NOT NULL,
  depart_id  INTEGER NOT NULL,
  CONSTRAINT fk_emp_dep FOREIGN KEY (depart_id) REFERENCES departs(id)
);

CREATE TABLE meets
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  date        TIMESTAMP NOT NULL,
  theme       VARCHAR(60) NOT NULL,
  employer_id  INTEGER NOT NULL,
  CONSTRAINT fk_emp_meet FOREIGN KEY (employer_id) REFERENCES employers(id)
);

CREATE TABLE members
(
  id             INTEGER PRIMARY KEY AUTO_INCREMENT,
  meet_id       INTEGER NOT NULL,
  employer_id   INTEGER NOT NULL,
  CONSTRAINT fk_memb_meet FOREIGN KEY (meet_id) REFERENCES meets(id) ON DELETE CASCADE
);




