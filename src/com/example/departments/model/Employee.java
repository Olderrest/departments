package com.example.departments.model;

import java.util.Date;

public class Employee extends Entity {
    private String firstName;
    private String lastName;
    private String email;
    private int salary;
    private Date hiringDay;
    private int departmentId;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, int salary, Date hiringDay, int departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.hiringDay = hiringDay;
        this.departmentId = departmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getHiringDay() {
        return hiringDay;
    }

    public void setHiringDay(Date hiringDay) {
        this.hiringDay = hiringDay;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", hiringDay=" + hiringDay +
                ", departmentId=" + departmentId +
                '}';
    }
}
