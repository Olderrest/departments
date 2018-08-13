package com.example.departments.db.dao;

import com.example.departments.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void add(Employee employee);

    void update(Employee employee);

    Employee findEmployeeById(int id);

    Employee findEmployeeByEmail(String email);

    List<String> findAllEmails();

    void deleteById(int id);
}
