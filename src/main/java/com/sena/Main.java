package com.sena;

import com.sena.database.ConnectionDB;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectionDB.getConnection()) {
            System.out.println("Conexión exitosa con la base de datos.");
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return;
        }
    }
}
