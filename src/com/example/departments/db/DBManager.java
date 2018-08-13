package com.example.departments.db;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static DBManager instance;
    private DataSource ds;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            InitialContext cxt = new InitialContext();
            ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/db" );
            if ( ds == null ) {
                throw new Exception("Data source not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Error getting connection", e);
        }
        return con;
    }
}
