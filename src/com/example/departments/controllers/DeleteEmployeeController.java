package com.example.departments.controllers;

import com.example.departments.services.EmployeeService;
import com.example.departments.services.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee_delete")
public class DeleteEmployeeController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter("id"));
        int departmentId = Integer.parseInt(req.getParameter("department"));
        employeeService.deleteById(employeeId);
        resp.sendRedirect("/employees?id="+departmentId);
    }
}
