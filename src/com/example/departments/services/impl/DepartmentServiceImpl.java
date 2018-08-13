package com.example.departments.services.impl;

import com.example.departments.db.dao.DepartmentDao;
import com.example.departments.db.dao.EmployeeDao;
import com.example.departments.db.dao.impl.DepartmentDaoImpl;
import com.example.departments.model.Department;
import com.example.departments.model.Employee;
import com.example.departments.services.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private static DepartmentDao departmentDao = null;

    public static synchronized DepartmentDao getInstance() {
        if (departmentDao == null) {
            departmentDao = new DepartmentDaoImpl();
        }
        return departmentDao;
    }


    @Override
    public void add(Department department) {
        getInstance().add(department);
    }

    @Override
    public void update(Department department) {
        getInstance().update(department);
    }

    @Override
    public Department findById(int id) {
        return getInstance().findById(id);
    }

    @Override
    public List<Department> getDepartmentsList() {
        return getInstance().getDepartmentsList();
    }

    @Override
    public List<Employee> getEmployeeListForDepartment(int id) {
        return getInstance().getEmployeesListForDepartment(id);
    }

    @Override
    public List<String> findAllNames() {
        return getInstance().findAllNames();
    }

    @Override
    public void deleteById(int id) {
        getInstance().deleteById(id);
    }
}
