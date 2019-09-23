CREATE DATABASE project1;

CREATE SCHEMA projectgenius;

CREATE TABLE employeeinfo (
employee_id SERIALIZABLE,
employee_first_name NOT NULL CHARACTER,
employee_last_name NOT NULL CHARACTER,
phone_number NULL NUMERIC(10), 
email UNIQUE varchar(50) NOT NULL,
password NOT NULL varchar(20),
);

CREATE TABLE employee_reimbursements(

);




