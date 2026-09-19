package com.sena;

import com.sena.controller.UserController;
import com.sena.database.ConnectionDB;
import com.sena.model.User;
import com.sena.repository.UserRepositoryImpl;
import com.sena.service.UserService;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionDB.getConnection();
            System.out.println("Conexion exitosa con la base de datos");
            connection.close();
        } catch (Exception e) {
            System.out.println("Error de connexion: " + e.getMessage());
        }

        // PRUEBAS DE MÉTODOS DEL USERCONTROLLER
        try {

            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            UserService userService = new UserService(userRepository);
            UserController userController = new UserController(userService);

            // 1. PRUEBA DE SAVEUSER (Crear usuario)
            System.out.println("\n1. Probando saveUser()...");
            User nuevoUsuario = new User();
            nuevoUsuario.setName("Laura");
            nuevoUsuario.setSurname("Gómez");
            nuevoUsuario.setEmail("laura.gomez@test.com");
            nuevoUsuario.setPassword("password123");
            nuevoUsuario.setDateOfBirth(new Date());
            nuevoUsuario.setWeight(62.0);
            nuevoUsuario.setHeight(1.68);
            nuevoUsuario.setObjective("Perder peso");

            User usuarioGuardado = userController.saveUser(nuevoUsuario);
            Long idGenerado = usuarioGuardado.getIdUser();
            System.out.println("-> Usuario guardado con éxito. ID generado: " + idGenerado);


            // 2. PRUEBA DE FINDALLUSERS
            System.out.println("\n2. Probando findAllUsers()...");
            List<User> listaUsuarios = userController.findAllUsers();
            System.out.println("-> Cantidad de usuarios en BD: " + listaUsuarios.size());
            for (User u : listaUsuarios) {
                System.out.println("   - [" + u.getIdUser() + "] " + u.getName() + " " + u.getSurname() + " (" + u.getEmail() + ")");
            }


            // 3. PRUEBA DE FINDUSERBYID
            System.out.println("\n3. Probando findUserById(" + idGenerado + ")...");
            User usuarioEncontrado = userController.findUserById(idGenerado);
            if (usuarioEncontrado != null) {
                System.out.println("-> Usuario encontrado:");
                System.out.println("   Nombre completo: " + usuarioEncontrado.getName() + " " + usuarioEncontrado.getSurname());
                System.out.println("   Objetivo actual: " + usuarioEncontrado.getObjective());
            } else {
                System.out.println("-> No se encontró el usuario con ID: " + idGenerado);
            }


            // 4. PRUEBA DE UPDATEUSER
            System.out.println("\n4. Probando updateUser()...");
            if (usuarioEncontrado != null) {
                usuarioEncontrado.setName("Laura Sofia");
                usuarioEncontrado.setWeight(60.5);
                usuarioEncontrado.setObjective("Mantenimiento");

                userController.updateUser(usuarioEncontrado);

                // Verificación en la base de datos
                User usuarioActualizado = userController.findUserById(idGenerado);
                System.out.println("-> Usuario actualizado con éxito:");
                System.out.println("   Nuevo Nombre: " + usuarioActualizado.getName());
                System.out.println("   Nuevo Peso: " + usuarioActualizado.getWeight() + " kg");
                System.out.println("   Nuevo Objetivo: " + usuarioActualizado.getObjective());
            }


            // 5. PRUEBA DE DELETEBYID
            System.out.println("\n5. Probando deleteById(" + idGenerado + ")...");
            userController.deleteById(idGenerado);
            System.out.println("-> Eliminación ejecutada.");

            // Verificación en base de datos
            User usuarioEliminado = userController.findUserById(idGenerado);
            if (usuarioEliminado == null) {
                System.out.println("-> Confirmación: El usuario con ID " + idGenerado + " fue borrado correctamente.");
            } else {
                System.out.println("-> Error: El usuario aún existe en la BD.");
            }

        } catch (Exception e) {
            System.out.println("Error durante la ejecución de las pruebas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
