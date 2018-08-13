package com.example.departments.services.impl;

import com.example.departments.db.dao.EmployeeDao;
import com.example.departments.db.dao.impl.EmployeeDaoImpl;
import com.example.departments.model.Employee;
import com.example.departments.services.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeDao employeeDao = null;

    public static synchronized EmployeeDao getInstance() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    @Override
    public void add(Employee employee) {
        getInstance().add(employee);
    }

    @Override
    public void update(Employee employee) {
        getInstance().update(employee);
    }

    @Override
    public Employee findEmployeeById(int id) {
        return getInstance().findEmployeeById(id);
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return getInstance().findEmployeeByEmail(email);
    }

    @Override
    public List<String> findAllEmails() {
        return getInstance().findAllEmails();
    }

    @Override
    public void deleteById(int id) {
        getInstance().deleteById(id);
    }
}
