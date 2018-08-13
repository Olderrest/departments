package com.example.departments.db.dao.impl;

import com.example.departments.db.DBManager;
import com.example.departments.db.dao.EmployeeDao;
import com.example.departments.model.Employee;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

    private DBManager manager = DBManager.getInstance();

    private static final String SQL_ADD_EMPLOYEE = "INSERT INTO employees(first_name,last_name, email, salary, hiring_day, department_id) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_EMPLOYEE = "UPDATE employees SET first_name=?, last_name=?, email=?, salary=?, hiring_day=?, department_id=? WHERE id=?";
    private static final String SQL_FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id=?";
    private static final String SQL_FIND_EMPLOYEE_BY_EMAIL = "SELECT * FROM employees WHERE email=?";
    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employees WHERE id=?";
    private static final String SQL_FIND_ALL_EMAILS = "SELECT email FROM employees";

    @Override
    public void add(Employee employee) {
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_ADD_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setInt(4, employee.getSalary());
                ps.setDate(5, new java.sql.Date(employee.getHiringDay().getTime()));
                ps.setInt(6, employee.getDepartmentId());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        employee.setId(rs.getInt(1));
                    }
                }
                connection.commit();
                logger.info("New employee added. Employee info: " + employee.toString());
            }
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during add employee");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    @Override
    public void update(Employee employee) {
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_EMPLOYEE)) {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setInt(4, employee.getSalary());
                ps.setDate(5, new java.sql.Date(employee.getHiringDay().getTime()));
                ps.setInt(6, employee.getDepartmentId());
                ps.setInt(7, employee.getId());
                ps.executeUpdate();
                connection.commit();
                logger.info("Employee updated. Employee info: " + employee.toString());
            }
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during update employee");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    @Override
    public Employee findEmployeeById(int id) {
        Connection connection = null;
        Employee employee = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_EMPLOYEE_BY_ID)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        employee = extractEmployee(rs);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during find employee");
            e.printStackTrace();
        } finally {
            close(connection);
        }
        if (employee != null){
            logger.info("Employee found. Employee info: " + employee.toString());
        }else {
            logger.info("Employee not found");
        }
        return employee;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        Connection connection = null;
        Employee employee = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_EMPLOYEE_BY_EMAIL)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        employee = extractEmployee(rs);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during find employee");
            e.printStackTrace();
        } finally {
            close(connection);
        }
        if (employee != null){
            logger.info("Employee found. Employee info: " + employee.toString());
        }else {
            logger.info("Employee not found");
        }
        return employee;
    }

    @Override
    public void deleteById(int id) {
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_EMPLOYEE)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
                logger.info("Employee deleted");
            }
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during delete employee");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    @Override
    public List<String> findAllEmails() {
        List<String> emails = new LinkedList<>();
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL_EMAILS)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String email = rs.getString("email");
                        emails.add(email);
                    }
                }
            }
            connection.commit();
            logger.info("List of emails is filled up");
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during find list of emails");
            e.printStackTrace();
        } finally {
            close(connection);
        }

        return emails;
    }

    private Employee extractEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setEmail(rs.getString("email"));
        employee.setSalary(rs.getInt("salary"));
        employee.setHiringDay(rs.getDate("hiring_day"));
        employee.setDepartmentId(rs.getInt("department_id"));
        return employee;
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
