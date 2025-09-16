package com.proyecto.v1.repository;

import java.sql.*;

public class Conexion {
    private static final String url= "jdbc:postgresql://localhost:5432/nombreBD";
    private static final String user= "postgres";
    private static final String password = "postgres";

    public static Connection getConnection(){
        try {
            return  DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
