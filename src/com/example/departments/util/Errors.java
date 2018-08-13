package com.example.departments.util;


public class Errors {
    private String firstNameError;
    private String lastNameError;
    private String emailError;
    private String salaryError;
    private String departmentNameError;
    private String dateError;


    public String getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getSalaryError() {
        return salaryError;
    }

    public void setSalaryError(String salaryError) {
        this.salaryError = salaryError;
    }

    public String getDepartmentNameError() {
        return departmentNameError;
    }

    public void setDepartmentNameError(String departmentNameError) {
        this.departmentNameError = departmentNameError;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }

    public boolean hasErrors() {
        if (firstNameError != null || lastNameError != null || emailError != null || salaryError != null || departmentNameError != null || dateError != null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Errors{" +
                "firstNameError='" + firstNameError + '\'' +
                ", lastNameError='" + lastNameError + '\'' +
                ", emailError='" + emailError + '\'' +
                ", salaryError='" + salaryError + '\'' +
                ", departmentNameError='" + departmentNameError + '\'' +
                ", dateError='" + dateError + '\'' +
                '}';
    }
}
