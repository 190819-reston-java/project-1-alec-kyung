CREATE DATABASE projectone;

CREATE SCHEMA ers;

--DROP TABLE ers.employees;
CREATE TABLE ers.employees (
emp_id SERIAL NOT NULL,
email varchar(50) UNIQUE NOT NULL,
pwd varchar(20) NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
phone_number NUMERIC(10) NOT NULL,
manager boolean DEFAULT FALSE NOT NULL
);

--DROP TABLE ers.reimbursements;
CREATE TABLE ers.reimbursements(
reimb_id SERIAL NOT NULL,
amount NUMERIC (10, 2) NOT NULL,
status VARCHAR (30) NOT NULL,
submitted_by_id INTEGER NOT NULL,
resolved_by_id INTEGER NOT NULL,
submit_time TIMESTAMP
);