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

@WebServlet("/update_department")
public class UpdateDepartmentController extends HttpServlet{

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String departmentId = request.getParameter("departmentId");
        Department department = departmentService.findById(Integer.parseInt(departmentId));

        Validator validator = new Validator();
        Errors errors = null;
        if ((errors = validator.validateDepartment(name)).hasErrors()) {
            request.setAttribute("errors", errors);
            request.setAttribute("name", name);
            request.setAttribute("department", department);
            request.getRequestDispatcher("WEB-INF/pages/update_department.jsp").forward(request, response);
        }else {
            department.setName(name);
            departmentService.update(department);
            response.sendRedirect("/main");
        }
    }
}
