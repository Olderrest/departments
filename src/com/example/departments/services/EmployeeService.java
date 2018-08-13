package com.example.departments.services;


import com.example.departments.model.Employee;

import java.util.List;

public interface EmployeeService {
    void add(Employee employee);

    void update(Employee employee);

    Employee findEmployeeById(int id);

    Employee findEmployeeByEmail(String email);

    List<String> findAllEmails();

    void deleteById(int id);
}
