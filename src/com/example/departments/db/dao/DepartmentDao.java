package com.example.departments.db.dao;

import com.example.departments.model.Department;
import com.example.departments.model.Employee;

import java.util.List;

public interface DepartmentDao {
    void add(Department department);

    void update(Department department);

    Department findById(int id);

    List<Department> getDepartmentsList();

    List<Employee> getEmployeesListForDepartment(int id);

    List<String> findAllNames();

    void deleteById(int id);
}
