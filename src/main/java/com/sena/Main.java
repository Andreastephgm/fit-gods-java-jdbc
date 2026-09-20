package com.sena;

import com.sena.database.ConnectionDB;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionDB.getConnection();
            System.out.println("Conexion exitosa con la base de datos");
            connection.close();
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
    }
}

