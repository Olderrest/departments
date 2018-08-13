package com.example.departments.controllers;

import com.example.departments.model.Department;
import com.example.departments.services.DepartmentService;
import com.example.departments.services.impl.DepartmentServiceImpl;
import com.example.departments.util.Errors;
import com.example.departments.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_new_department")
public class AddDepartmentController extends HttpServlet {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department newDepartment = new Department();
        String name = req.getParameter("name");
        Validator validator = new Validator();
        Errors errors = null;
        if ((errors = validator.validateDepartment(name)).hasErrors()) {
            req.setAttribute("errors", errors);
            req.setAttribute("name", name);
            req.getRequestDispatcher("WEB-INF/pages/add_department.jsp").forward(req, resp);
        } else {
            newDepartment.setName(name);
            departmentService.add(newDepartment);
            resp.sendRedirect("/main");
        }
    }
}
