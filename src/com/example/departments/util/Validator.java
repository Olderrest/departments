package com.example.departments.util;

import com.example.departments.model.Employee;
import com.example.departments.services.DepartmentService;
import com.example.departments.services.EmployeeService;
import com.example.departments.services.impl.DepartmentServiceImpl;
import com.example.departments.services.impl.EmployeeServiceImpl;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Errors errors = new Errors();

    private DepartmentService departmentService = new DepartmentServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();

    private final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\." +
                    "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
                    "(\\.[A-Za-z]{2,})$";

    private final String NAME_PATTERN = "[_A-Za-z]{3,20}";

    public Errors validateEmployee(String firstName, String lastName, String email, String salary, String id, String date) {
        validateFirstName(firstName);
        validateLastName(lastName);
        validateEmail(email, id);
        validateSalary(salary);
        validateDate(date);
        return errors;
    }

    public Errors validateDepartment(String name) {
        validateDepartmentName(name);
        return errors;
    }

    private void validateEmail(String email, String id) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errors.setEmailError("Email isn't correct");
            return;
        }
        List<String> emails = employeeService.findAllEmails();
        Employee employee = null;
        if (id != null) {
            employee = employeeService.findEmployeeById(Integer.parseInt(id));
            if (employee.getEmail().equals(email)) {
                return;
            }
        }

        for (String value : emails) {
            if (value.equals(email)) {
                errors.setEmailError("Email already exist");
            }
        }
    }

    private void validateSalary(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                errors.setSalaryError("Salary isn't correct");
                return;
            }
        }
        int salary = Integer.parseInt(value.toString());
        if (salary < 50 || salary > 250000) {
            errors.setSalaryError("Salary isn't correct. Value must be between 50 and 250000");
        }
    }

    private void validateFirstName(String firstName) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(firstName);
        if (!matcher.matches()) {
            errors.setFirstNameError("First name isn't correct. Length must be between 3 and 20 characters and name have to not contain digits");
        }
    }

    private void validateLastName(String lastName) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(lastName);
        if (!matcher.matches()) {
            errors.setLastNameError("Last name isn't correct. Length must be between 3 and 20 characters and name have to not contain digits");
        }
    }

    private void validateDepartmentName(String name) {
        if (name.length() < 5 || name.length() > 30) {
            errors.setDepartmentNameError("Department name isn't correct. Length must be between 3 and 30 characters.");
            return;
        }
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                errors.setDepartmentNameError("Department name isn't correct. Name have to not contain digits");
                return;
            }
        }
        List<String> names = departmentService.findAllNames();
        for (String departName : names) {
            if (departName.equals(name)) {
                errors.setDepartmentNameError("That name already exist");
            }
        }
    }

    private void validateDate(String value) {
        if (value != null) {
            String[] dateArray = value.split("-");
            Calendar now = Calendar.getInstance();
            if (Integer.parseInt(dateArray[0]) != Integer.parseInt(String.valueOf(now.get(Calendar.YEAR)))) {
                errors.setDateError("Hiring date isn't correct");
                return;
            }
            if (Integer.parseInt(dateArray[1]) != Integer.parseInt(String.valueOf(now.get(Calendar.MONTH) + 1))) {
                errors.setDateError("Hiring date isn't correct");
                return;
            }
        }
    }
}
