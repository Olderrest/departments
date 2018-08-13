package com.example.departments.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class RedirectAddEmployeeController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentId = req.getParameter("departmentId");
        req.setAttribute("departmentId", departmentId);
        req.getRequestDispatcher("WEB-INF/pages/add_employee.jsp").forward(req,resp);
    }
}
