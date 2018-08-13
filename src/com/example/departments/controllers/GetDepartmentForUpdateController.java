package com.example.departments.controllers;

import com.example.departments.model.Department;
import com.example.departments.services.DepartmentService;
import com.example.departments.services.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/depart_update")
public class GetDepartmentForUpdateController extends HttpServlet {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentId = request.getParameter("id");
        Department department = departmentService.findById(Integer.parseInt(departmentId));
        request.setAttribute("department", department);
        request.setAttribute("name", department.getName());
        request.getRequestDispatcher("WEB-INF/pages/update_department.jsp").forward(request, response);
    }
}
