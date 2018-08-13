package com.example.departments.controllers;

import com.example.departments.model.Department;
import com.example.departments.model.Employee;
import com.example.departments.services.DepartmentService;
import com.example.departments.services.EmployeeService;
import com.example.departments.services.impl.DepartmentServiceImpl;
import com.example.departments.services.impl.EmployeeServiceImpl;
import com.example.departments.util.Errors;
import com.example.departments.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/update_employee")
public class UpdateEmployeeController extends HttpServlet{

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String salary = req.getParameter("salary");
        String departmentId = req.getParameter("department");
        List<Department> departments = departmentService.getDepartmentsList();
        Employee employee = employeeService.findEmployeeById(Integer.parseInt(id));

        Validator validator = new Validator();
        Errors errors = null;
        if ((errors = validator.validateEmployee(firstName, lastName, email, salary, id, null)).hasErrors()) {
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);
            req.setAttribute("firstName", firstName);
            req.setAttribute("lastName", lastName);
            req.setAttribute("email", email);
            req.setAttribute("salary", salary);
            req.setAttribute("departments", departments);
            req.setAttribute("departmentId", departmentId);
            req.getRequestDispatcher("WEB-INF/pages/update_employee.jsp").forward(req,resp);
        } else {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setSalary(Integer.parseInt(salary));
            employee.setDepartmentId(Integer.parseInt(departmentId));
            employeeService.update(employee);
            resp.sendRedirect("/employees?id=" + departmentId);
        }
    }
}
