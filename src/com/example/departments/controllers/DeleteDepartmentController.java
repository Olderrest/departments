package com.example.departments.controllers;

import com.example.departments.services.DepartmentService;
import com.example.departments.services.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/depart_delete")
public class DeleteDepartmentController extends HttpServlet {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentId = request.getParameter("id");
        departmentService.deleteById(Integer.parseInt(departmentId));
        response.sendRedirect("/main");
    }
}
