package com.example.departments.controllers;

import com.example.departments.model.Employee;
import com.example.departments.services.EmployeeService;
import com.example.departments.services.impl.EmployeeServiceImpl;
import com.example.departments.util.DateParser;
import com.example.departments.util.Errors;
import com.example.departments.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/add_employee")
public class AddEmployeeController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentId = req.getParameter("departmentId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("mail");
        String salary = req.getParameter("salary");
        String date = req.getParameter("hiringDay");
        Date hiringDay = DateParser.parseDate(date);

        Validator validator = new Validator();
        Errors errors = null;
        if ((errors = validator.validateEmployee(firstName, lastName, email, salary, null, date)).hasErrors()) {
            req.setAttribute("errors", errors);
            req.setAttribute("firstName", firstName);
            req.setAttribute("lastName", lastName);
            req.setAttribute("mail", email);
            req.setAttribute("salary", salary);
            req.setAttribute("departmentId", departmentId);
            req.setAttribute("hiringDay", hiringDay);
            req.getRequestDispatcher("WEB-INF/pages/add_employee.jsp").forward(req, resp);
        } else {
            employeeService.add(new Employee(firstName, lastName, email, Integer.parseInt(salary), hiringDay, Integer.parseInt(departmentId)));
            resp.sendRedirect("/employees?id=" + departmentId);
        }
    }
}
