package com.example.departments.db.dao.impl;

import com.example.departments.db.DBManager;
import com.example.departments.db.dao.DepartmentDao;
import com.example.departments.model.Department;
import com.example.departments.model.Employee;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class DepartmentDaoImpl implements DepartmentDao {
    private static final Logger logger = Logger.getLogger(DepartmentDaoImpl.class);

    private DBManager manager = DBManager.getInstance();

    private static final String SQL_ADD_DEPARTMENT = "INSERT INTO departments(name) VALUES(?)";
    private static final String SQL_ADD_EMPLOYEE_FROM_DEPARTMENT = "INSERT INTO employees(fist_name,last_name, email, salary, hiring_day, department_id) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_DEPARTMENT = "UPDATE departments SET name=? WHERE id=?";
    private static final String SQL_FIND_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE id=?";
    private static final String SQL_FIND_EMPLOYEES_FROM_DEPARTMENT = "SELECT * FROM employees WHERE department_id=?";
    private static final String SQL_FIND_ALL_DEPARTMENTS = "SELECT * FROM departments";
    private static final String SQL_FIND_ALL_NAMES = "SELECT name FROM departments";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM departments WHERE id=?";


    @Override
    public void add(Department department) {
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            if (!department.getEmployees().isEmpty()) {
                addEmployeeFromDepartment(connection, department);
            }
            try (PreparedStatement ps = connection.prepareStatement(SQL_ADD_DEPARTMENT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, department.getName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        department.setId(rs.getInt(1));
                    }
                }
                connection.commit();
                logger.info("New department added. Department info: " + department.toString());
            }
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during add department");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    @Override
    public void update(Department department) {
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_DEPARTMENT)) {
                ps.setString(1, department.getName());
                ps.setInt(2, department.getId());
                ps.executeUpdate();
                connection.commit();
                logger.info("Department updated. Department info: " + department.toString());
            }
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during update department");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    @Override
    public Department findById(int id) {
        Connection connection = null;
        Department department = null;
        List<Employee> employees = getEmployeesListForDepartment(id);
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_DEPARTMENT_BY_ID)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        department.setEmployees(employees);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during find department");
            e.printStackTrace();
        } finally {
            close(connection);
        }
        if (department != null) {
            logger.info("Department found. Department info: " + department.toString());
        } else {
            logger.info("Department not found");
        }
        return department;
    }

    @Override
    public List<Department> getDepartmentsList() {
        List<Department> departments = new LinkedList<>();
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL_DEPARTMENTS)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Department department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        department.setEmployees(getEmployeesListForDepartment(department.getId()));
                        departments.add(department);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during fill list of departments");
            e.printStackTrace();
        } finally {
            close(connection);
        }
        if (departments.size() > 0){
            logger.info("List of departments is full. List info: " + departments);
        }else {
            logger.info("List of departments is empty");
        }
        return departments;
    }

    @Override
    public List<Employee> getEmployeesListForDepartment(int id) {
        Connection connection = null;
        List<Employee> employees = new LinkedList<>();
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_EMPLOYEES_FROM_DEPARTMENT)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Employee employee = new Employee();
                        employee.setId(rs.getInt("id"));
                        employee.setFirstName(rs.getString("first_name"));
                        employee.setLastName(rs.getString("last_name"));
                        employee.setEmail(rs.getString("email"));
                        employee.setSalary(rs.getInt("salary"));
                        employee.setHiringDay(rs.getDate("hiring_day"));
                        employee.setDepartmentId(rs.getInt("department_id"));
                        employees.add(employee);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during fill list of employees");
            e.printStackTrace();
        } finally {
            close(connection);
        }
        if (employees.size() > 0){
            logger.info("List of employees is full. List info: " + employees);
        }else {
            logger.info("List of employees is empty");
        }
        return employees;
    }

    @Override
    public List<String> findAllNames() {
        List<String> names = new LinkedList<>();
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL_NAMES)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String email = rs.getString("name");
                        names.add(email);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during find list of names");
            e.printStackTrace();
        } finally {
            close(connection);
        }
        if (names.size() > 0){
            logger.info("List of names is full. List info: " + names);
        }else {
            logger.info("List of names is empty");
        }
        return names;
    }

    @Override
    public void deleteById(int id) {
        Connection connection = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_DEPARTMENT)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
                logger.info("Department deleted");
            }
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Error during delete department");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    private void addEmployeeFromDepartment(Connection connection, Department department) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_ADD_EMPLOYEE_FROM_DEPARTMENT)) {
            for (Employee employee : department.getEmployees()) {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setInt(4, employee.getSalary());
                ps.setDate(5, new java.sql.Date(employee.getHiringDay().getTime()));
                ps.setInt(6, employee.getDepartmentId());
                ps.addBatch();
            }
            ps.executeBatch();
        } finally {
            close(connection);
        }
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
