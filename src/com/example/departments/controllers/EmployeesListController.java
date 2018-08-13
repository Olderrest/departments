package com.example.departments.controllers;

import com.example.departments.model.Department;
import com.example.departments.model.Employee;
import com.example.departments.services.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeesListController extends HttpServlet {

    private DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentId = Integer.parseInt(request.getParameter("id"));
        Department department = departmentService.findById(departmentId);
        List<Employee> employees = department.getEmployees();
        request.setAttribute("employees", employees);
        request.setAttribute("department", department);
        request.getRequestDispatcher("WEB-INF/pages/employees.jsp").forward(request,response);
    }
}
