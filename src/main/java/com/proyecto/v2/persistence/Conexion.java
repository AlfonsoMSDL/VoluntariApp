package com.proyecto.v2.persistence;

import java.sql.*;

public class Conexion {
    private static final String url= "jdbc:postgresql://localhost:5432/voluntariAppBD";
    private static final String user= "postgres";
    private static final String password = "1234";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de PostgreSQL", e);
        }
        return  DriverManager.getConnection(url,user,password);
    }

    public static void close(Connection cn) throws SQLException {
        cn.close();
    }

    public static void close(PreparedStatement stmt) throws SQLException {
        stmt.close();
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }



}
