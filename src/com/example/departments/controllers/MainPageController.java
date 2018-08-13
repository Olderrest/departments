package com.example.departments.controllers;

import com.example.departments.model.Department;
import com.example.departments.services.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainPageController extends HttpServlet{

    private DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = departmentService.getDepartmentsList();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("WEB-INF/pages/departments.jsp").forward(request,response);
    }
}
