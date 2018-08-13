package com.example.departments.controllers;

import com.example.departments.model.Department;
import com.example.departments.model.Employee;
import com.example.departments.services.DepartmentService;
import com.example.departments.services.EmployeeService;
import com.example.departments.services.impl.DepartmentServiceImpl;
import com.example.departments.services.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee_update")
public class GetEmployeeForUpdateController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("id");

        Employee employee = employeeService.findEmployeeById(Integer.parseInt(employeeId));
        List<Department> departments = departmentService.getDepartmentsList();

        req.setAttribute("id", employeeId);
        req.setAttribute("departmentId", employee.getDepartmentId());
        req.setAttribute("firstName", employee.getFirstName());
        req.setAttribute("lastName", employee.getLastName());
        req.setAttribute("email", employee.getEmail());
        req.setAttribute("salary", employee.getSalary());
        req.setAttribute("departments", departments);

        req.getRequestDispatcher("WEB-INF/pages/update_employee.jsp").forward(req,resp);
    }
}
