CREATE DATABASE projectone;

CREATE SCHEMA ers;

DROP TABLE ers.employees;
CREATE TABLE ers.employees (
emp_id SERIAL PRIMARY KEY NOT NULL,
email varchar(50) UNIQUE NOT NULL,
pwd varchar(20) NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
phone_number NUMERIC(10) NOT NULL,
manager boolean DEFAULT FALSE NOT NULL
);

SELECT * FROM ers.employees;

INSERT INTO ers.employees VALUES
	(DEFAULT, 'revature@gmail.com', 'revature', 'John', 'Smith', 7031234567, TRUE),
	(DEFAULT, 'work@gmail.com', 'iwantmoney', 'Kyung', 'Lee', 7032223333, DEFAULT);

DROP TABLE ers.reimbursements;
CREATE TABLE ers.reimbursements(
reimb_id SERIAL NOT NULL,
amount NUMERIC (10, 2) NOT NULL,
status VARCHAR (30) NOT NULL,
submitted_by_id INTEGER REFERENCES ers.employees(emp_id) NOT NULL,
resolved_by_id INTEGER REFERENCES ers.employees(emp_id),
image_url VARCHAR (100),
submit_time TIMESTAMP
);

SELECT * FROM ers.reimbursements;

INSERT INTO ers.reimbursements VALUES
	(DEFAULT, 123.45, 'approved', 2, 1, NULL, DEFAULT),
	(DEFAULT, 75.21, 'denied', 2, 1, NULL, DEFAULT),
	(DEFAULT, 50.50, 'submitted', 2, NULL, NULL, DEFAULT),
	(DEFAULT, 2.33, 'submitted', 2, NULL, NULL, DEFAULT);

--This shows resolved reimbursements
SELECT * 
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.resolved_by_id IS NOT NULL;

--This shows submitted BUT NOT resolved reimbursements
SELECT * 
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.resolved_by_id IS NULL AND ers.reimbursements.submitted_by_id IS NOT NULL;

--This shows every employee who is NOT manager
SELECT emp_id, email, first_name, last_name, phone_number
FROM ers.employees
WHERE manager = FALSE;

------------------------------------------------------PROJECT REQUIREMENTS

--An Employee can view their pending reimbursement requests
-- SET THE NUMBER to ? for java
SELECT * 
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.resolved_by_id IS NULL AND ers.reimbursements.submitted_by_id IS NOT NULL AND ers.reimbursements.submitted_by_id = ?;

--An Employee can view their resolved reimbursement requests
-- SET THE NUMBER to ? for java
SELECT * 
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.resolved_by_id IS NOT NULL AND ers.reimbursements.submitted_by_id = ?;

--An Employee can view their information    (SAME FOR MANAGER)
-- SET THE NUMBER to ? for java
SELECT *
FROM ers.employees
WHERE emp_id = ?;

--An Employee can update their information
-- CANNOT UPDATE IF MANAGER OR NOT
--?? FOR JAVA
UPDATE ers.employees
SET emp_id = ?, email = ?, pwd = ?, first_name = ?, last_name = ?, phone_number = ?;

--A Manager can approve/deny pending reimbursement requests
--???? FOR JAVA
UPDATE ers.reimbursements
SET status =?
WHERE reimb_id = ?;

--A Manager can view all pending requests from all employees
--NO SESSION REQUIRED, STATIC SQL SCRIPT
SELECT * 
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.resolved_by_id IS NULL AND ers.reimbursements.submitted_by_id IS NOT NULL;

--Manager see all resolved from all emplyeees
SELECT * 
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.status = 'approved' OR ers.reimbursements.status = 'denied';

SELECT ers.employees.email, ers.employees.first_name, ers.employees.last_name, ers.reimbursements.amount, ers.reimbursements.status, ers.reimbursements.image_url 
FROM ers.employees 
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.status = 'approved' OR ers.reimbursements.status = 'denied';

--A Manager can view all resolved requests from all employees and see which manager resolved it
--NO SESSION REQUIRED
SELECT emp_id, first_name, last_name
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.resolved_by_id
WHERE ers.reimbursements.resolved_by_id IS NOT NULL AND ers.reimbursements.reimb_id = 2;

--A Manager can view all Employees
--STATIC SCRIPT, NO SESSION REQUIRED
SELECT *
FROM ers.employees;

--A Manager can view reimbursement requests from a single Employee
--??? FOR JAVA
SELECT * 
FROM ers.employees
FULL JOIN ers.reimbursements
ON ers.employees.emp_id = ers.reimbursements.submitted_by_id
WHERE ers.reimbursements.resolved_by_id IS NULL AND ers.reimbursements.submitted_by_id IS NOT NULL AND ers.reimbursements.submitted_by_id = ?;

