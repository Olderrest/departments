DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

---------------------
-- table departments
---------------------
CREATE TABLE IF NOT EXISTS departments(
  id  serial PRIMARY KEY,
  name VARCHAR(30) NOT NULL UNIQUE
);

-----------------------
--table employees
-----------------------
CREATE TABLE IF NOT EXISTS employees (
  id  serial PRIMARY KEY,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL UNIQUE,
  salary INTEGER NOT NULL,
  hiring_day DATE NOT NULL,
  department_id INTEGER NOT NULL REFERENCES departments(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO departments(name) VALUES ('Housing');
INSERT INTO departments(name) VALUES ('Finance');
INSERT INTO departments(name) VALUES ('Education');

INSERT INTO employees(first_name, last_name, email, salary, hiring_day, department_id) VALUES ('Josh', 'Smith', 'jsmith@gmail.com', '2000', '10.08.2018', 1);
INSERT INTO employees(first_name, last_name, email, salary, hiring_day, department_id) VALUES ('Harrison', 'Drake', 'drake@gmail.com', '150', '09.08.2018', 3);
INSERT INTO employees(first_name, last_name, email, salary, hiring_day, department_id) VALUES ('David', 'Blake', 'D_blake@gmail.com', '2350', '08.08.2018', 3);