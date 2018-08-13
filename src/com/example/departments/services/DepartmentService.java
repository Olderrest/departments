package com.example.departments.services;

import com.example.departments.model.Department;
import com.example.departments.model.Employee;

import java.util.List;

public interface DepartmentService {

    void add(Department department);

    void update(Department department);

    Department findById(int id);

    List<Department> getDepartmentsList();

    List<Employee> getEmployeeListForDepartment(int id);

    List<String> findAllNames();

    void deleteById(int id);
}
