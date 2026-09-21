package com.sena;

import com.sena.controller.RoutineController;
import com.sena.controller.UserController;
import com.sena.model.*;
import com.sena.repository.*;
import com.sena.service.RoutineService;
import com.sena.service.UserService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // =========================================================================
        // 1. PRUEBAS DE CONTROLADORES Y CAPAS DE SERVICIO (USER & ROUTINE)
        // =========================================================================
        try {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            UserService userService = new UserService(userRepository);
            UserController userController = new UserController(userService);

            RoutineRepositoryImpl routineRepository = new RoutineRepositoryImpl();
            RoutineService routineService = new RoutineService(routineRepository);
            RoutineController routineController = new RoutineController(routineService);

            System.out.println("========== PRUEBAS DE USERCONTROLLER ==========");

            System.out.println("\n1. Registrando usuario...");
            User nuevoUsuario = new User();
            nuevoUsuario.setName("Carlos");
            nuevoUsuario.setSurname("Pérez");
            nuevoUsuario.setEmail("carlos.perez@test.com");
            nuevoUsuario.setPassword("pass2026");
            nuevoUsuario.setDateOfBirth(new Date());
            nuevoUsuario.setWeight(75.0);
            nuevoUsuario.setHeight(1.78);
            nuevoUsuario.setObjective("Aumento de fuerza");

            User usuarioGuardado = userController.saveUser(nuevoUsuario);
            Long idUsuarioGenerado = usuarioGuardado.getIdUser();
            System.out.println("-> Registro de usuario exitoso. ID: " + idUsuarioGenerado);

            System.out.println("\n2. Consultando listado de usuarios...");
            List<User> listaUsuarios = userController.findAllUsers();
            System.out.println("-> Total usuarios en sistema: " + listaUsuarios.size());
            for (User u : listaUsuarios) {
                System.out.println("   - [" + u.getIdUser() + "] " + u.getName() + " " + u.getSurname() + " (" + u.getEmail() + ")");
            }

            System.out.println("\n3. Buscando usuario por ID (" + idUsuarioGenerado + ")...");
            User usuarioEncontrado = userController.findUserById(idUsuarioGenerado);
            if (usuarioEncontrado != null) {
                System.out.println("-> Usuario localizado: " + usuarioEncontrado.getName() + " " + usuarioEncontrado.getSurname());
            }

            System.out.println("\n4. Modificando datos de usuario...");
            if (usuarioEncontrado != null) {
                usuarioEncontrado.setName("Carlos Alberto");
                usuarioEncontrado.setWeight(73.5);
                usuarioEncontrado.setObjective("Recomposición corporal");

                userController.updateUser(usuarioEncontrado);

                User usuarioActualizado = userController.findUserById(idUsuarioGenerado);
                System.out.println("-> Datos actualizados: " + usuarioActualizado.getName() + " | Peso actual: " + usuarioActualizado.getWeight() + " kg");
            }

            System.out.println("\n========== PRUEBAS DE ROUTINECONTROLLER ==========");

            System.out.println("\n5. Registrando rutina para el usuario...");
            Routine nuevaRutina = new Routine();
            nuevaRutina.setName("Rutina Torso / Pierna");
            nuevaRutina.setObjective("Fuerza 4 días");
            nuevaRutina.setIdUser(idUsuarioGenerado);

            Routine rutinaGuardada = routineController.saveRoutine(nuevaRutina);
            Long idRutinaGenerado = rutinaGuardada.getIdRoutine();
            System.out.println("-> Rutina registrada correctamente. ID: " + idRutinaGenerado + " (Usuario ID: " + idUsuarioGenerado + ")");

            System.out.println("\n6. Consultando listado de rutinas...");
            List<Routine> listaRutinas = routineController.FindAllRoutines();
            System.out.println("-> Total rutinas en sistema: " + listaRutinas.size());
            for (Routine r : listaRutinas) {
                System.out.println("   - [ID Rutina: " + r.getIdRoutine() + "] " + r.getName() + " | ID Usuario: " + r.getIdUser());
            }

            System.out.println("\n7. Buscando rutina por ID (" + idRutinaGenerado + ")...");
            Routine rutinaEncontrada = routineController.FinfByIdRoutine(idRutinaGenerado);
            if (rutinaEncontrada != null) {
                System.out.println("-> Rutina localizada: " + rutinaEncontrada.getName() + " | Objetivo: " + rutinaEncontrada.getObjective());
            }

            System.out.println("\n8. Modificando información de rutina...");
            if (rutinaEncontrada != null) {
                rutinaEncontrada.setName("Rutina Torso / Pierna Avanzada");
                rutinaEncontrada.setObjective("Fuerza e hipertrofia");

                routineController.UpdateRoutine(rutinaEncontrada);

                Routine rutinaActualizada = routineController.FinfByIdRoutine(idRutinaGenerado);
                System.out.println("-> Rutina modificada: " + rutinaActualizada.getName() + " | Objetivo: " + rutinaActualizada.getObjective());
            }

            System.out.println("\n9. Eliminando rutina ID (" + idRutinaGenerado + ")...");
            routineController.deleteRoutineById(idRutinaGenerado);

            Routine rutinaEliminada = routineController.FinfByIdRoutine(idRutinaGenerado);
            if (rutinaEliminada == null) {
                System.out.println("-> Verificación: Rutina removida de la base de datos.");
            }

            System.out.println("\n10. Eliminando usuario ID (" + idUsuarioGenerado + ")...");
            userController.deleteById(idUsuarioGenerado);

            User usuarioEliminado = userController.findUserById(idUsuarioGenerado);
            if (usuarioEliminado == null) {
                System.out.println("-> Verificación: Usuario removido de la base de datos.");
            }

        } catch (Exception e) {
            System.err.println("Excepción durante pruebas de controladores: " + e.getMessage());
            e.printStackTrace();
        }

        // =========================================================================
        // 2. PRUEBAS DE ROUTINEREPOSITORY
        // =========================================================================
        try {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            RoutineRepositoryImpl routineRepository = new RoutineRepositoryImpl();

            User usuarioPrueba = new User();
            usuarioPrueba.setName("Javier");
            usuarioPrueba.setSurname("Gómez");
            usuarioPrueba.setEmail("javier." + System.currentTimeMillis() + "@test.com");
            usuarioPrueba.setDateOfBirth(java.sql.Date.valueOf("1995-08-20"));
            usuarioPrueba.setWeight(70.0);
            usuarioPrueba.setPassword("securePass1");
            usuarioPrueba.setObjective("Mantenimiento");
            usuarioPrueba = userRepository.save(usuarioPrueba);

            Long userId = usuarioPrueba.getIdUser();

            System.out.println("\n========== PRUEBAS DE ROUTINEREPOSITORY ==========");

            System.out.println("\n1. Registrando rutina directa...");
            Routine nuevaRutina = new Routine();
            nuevaRutina.setName("Rutina Push / Pull / Legs");
            nuevaRutina.setObjective("Frecuencia 2 semanal");
            nuevaRutina.setIdUser(userId);

            Routine rutinaGuardada = routineRepository.save(nuevaRutina);
            Long idRutina = rutinaGuardada.getIdRoutine();
            System.out.println("-> Registro de rutina correcto. ID: " + idRutina);

            System.out.println("\n2. Consultando todas las rutinas...");
            List<Routine> lista = routineRepository.findAll();
            System.out.println("-> Registros encontrados: " + lista.size());
            for (Routine r : lista) {
                System.out.println("   - [ID: " + r.getIdRoutine() + "] " + r.getName() + " | Objetivo: " + r.getObjective() + " | UserID: " + r.getIdUser());
            }

            System.out.println("\n3. Buscando registro por ID (" + idRutina + ")...");
            Routine encontrada = routineRepository.findById(idRutina);
            if (encontrada != null) {
                System.out.println("-> Registro cargado: " + encontrada.getName() + " | Pertenece a User ID: " + encontrada.getIdUser());
            }

            System.out.println("\n4. Actualizando datos de la rutina...");
            if (encontrada != null) {
                encontrada.setName("Rutina PPL - Enfocado en Fuerza");
                encontrada.setObjective("Cargas progresivas en básicos");
                routineRepository.update(encontrada);

                Routine actualizada = routineRepository.findById(idRutina);
                System.out.println("-> Datos guardados: " + actualizada.getName() + " | Nuevo enfoque: " + actualizada.getObjective());
            }

            System.out.println("\n5. Eliminando registro de rutina...");
            routineRepository.deleteById(idRutina);

            Routine eliminada = routineRepository.findById(idRutina);
            if (eliminada == null) {
                System.out.println("-> Verificación: Rutina " + idRutina + " eliminada.");
            }

            userRepository.deleteById(userId);

        } catch (Exception e) {
            System.err.println("Excepción durante pruebas de RoutineRepository: " + e.getMessage());
            e.printStackTrace();
        }

        // =========================================================================
        // 3. PRUEBAS DE PROGRESSREPOSITORY
        // =========================================================================
        try {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            ProgressRepositoryImpl progressRepository = new ProgressRepositoryImpl();

            User usuarioPrueba = new User();
            usuarioPrueba.setName("Mariana");
            usuarioPrueba.setSurname("López");
            usuarioPrueba.setEmail("mariana." + System.currentTimeMillis() + "@test.com");
            usuarioPrueba.setDateOfBirth(java.sql.Date.valueOf("2000-03-10"));
            usuarioPrueba.setPassword("mariana2026");
            usuarioPrueba.setWeight(55.0);
            usuarioPrueba.setObjective("Aumento de peso");

            usuarioPrueba = userRepository.save(usuarioPrueba);
            Long userId = usuarioPrueba.getIdUser();

            System.out.println("\n========== PRUEBAS DE PROGRESS REPOSITORY ==========");

            System.out.println("\n1. Registrando avance diario...");
            Progress nuevoProgreso = new Progress();
            nuevoProgreso.setDate(LocalDate.now());
            nuevoProgreso.setWeight(55.8);
            nuevoProgreso.setNotes("Mejora de marcas en Press de Banca y Sentadilla");
            nuevoProgreso.setIdUser(userId);

            Progress guardado = progressRepository.save(nuevoProgreso);
            Long idProgreso = guardado.getIdProgress();
            System.out.println("-> Seguimiento guardado. ID: " + idProgreso);

            System.out.println("\n2. Consultando la lista de progresos...");
            List<Progress> lista = progressRepository.findAll();
            System.out.println("-> Cantidad de registros en BD: " + lista.size());
            for (Progress p : lista) {
                System.out.println("   - [ID: " + p.getIdProgress() + "] Fecha: " + p.getDate() +
                        " | Peso: " + p.getWeight() + "kg | Notas: " + p.getNotes() +
                        " | User ID: " + p.getIdUser());
            }

            System.out.println("\n3. Buscando registro de progreso por ID (" + idProgreso + ")...");
            Progress encontrado = progressRepository.findById(idProgreso);
            if (encontrado != null) {
                System.out.println("-> Registro ubicado. Peso cargado: " + encontrado.getWeight() + "kg");
            }

            System.out.println("\n4. Modificando registro de progreso...");
            if (encontrado != null) {
                encontrado.setWeight(56.2);
                encontrado.setNotes("Evaluación semanal: buena adaptación al plan de cargas");
                progressRepository.update(encontrado);

                Progress actualizado = progressRepository.findById(idProgreso);
                System.out.println("-> Datos actualizados. Nuevo peso: " + actualizado.getWeight() + "kg | Nota: " + actualizado.getNotes());
            }

            System.out.println("\n5. Removiendo registro de progreso...");
            progressRepository.deleteById(idProgreso);

            Progress eliminado = progressRepository.findById(idProgreso);
            if (eliminado == null) {
                System.out.println("-> Verificación: Registro " + idProgreso + " removido.");
            }

            userRepository.deleteById(userId);

        } catch (Exception e) {
            System.err.println("Excepción durante pruebas de ProgressRepository: " + e.getMessage());
            e.printStackTrace();
        }

        // =========================================================================
        // 4. PRUEBAS DE ROUTINEEXERCISEREPOSITORY (TABLA PIVOTE)
        // =========================================================================
        try {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            RoutineRepositoryImpl routineRepository = new RoutineRepositoryImpl();
            ExcerciseRepositoryImpl excerciseRepository = new ExcerciseRepositoryImpl();
            RoutineExerciseRepositoryImpl routineExerciseRepository = new RoutineExerciseRepositoryImpl();

            User user = new User();
            user.setName("Mateo");
            user.setSurname("Ramírez");
            user.setEmail("mateo." + System.currentTimeMillis() + "@test.com");
            user.setPassword("pass321");
            user.setDateOfBirth(java.sql.Date.valueOf("1997-11-05"));
            user.setWeight(78.0);
            user.setObjective("Resistencia muscular");
            user = userRepository.save(user);

            Routine routine = new Routine();
            routine.setName("Entrenamiento de Espalda y Biceps");
            routine.setObjective("Tracción y volumen");
            routine.setIdUser(user.getIdUser());
            routine = routineRepository.save(routine);

            Excercise excercise = new Excercise();
            excercise.setName("Remo con Barra");
            excercise.setDescription("Tracción horizontal para zona media del torso");
            excercise.setMuscularGroup("Espalda");
            excercise = excerciseRepository.save(excercise);

            System.out.println("\n========== PRUEBAS DE ROUTINE EXERCISE REPOSITORY ==========");

            System.out.println("\n1. Asociando ejercicio a la rutina...");
            RoutineExcercise nuevoRegistro = new RoutineExcercise();
            nuevoRegistro.setIdRoutine(routine.getIdRoutine());
            nuevoRegistro.setIdExercise(excercise.getIdExercise());
            nuevoRegistro.setSeries(4);
            nuevoRegistro.setRepetitions(12);
            nuevoRegistro.setRest(60);

            RoutineExcercise guardado = routineExerciseRepository.save(nuevoRegistro);
            Long idGenerado = guardado.getIdRoutineExcercise();
            System.out.println("-> Vínculo guardado correctamente. ID asignado: " + idGenerado);

            System.out.println("\n2. Listando registros de la tabla pivote...");
            List<RoutineExcercise> lista = routineExerciseRepository.findAll();
            System.out.println("-> Registros de asociación encontrados: " + lista.size());
            for (RoutineExcercise re : lista) {
                System.out.println("   - [ID: " + re.getIdRoutineExcercise() + "] Routine ID: " + re.getIdRoutine() +
                        " | Exercise ID: " + re.getIdExercise() +
                        " | Series: " + re.getSeries() +
                        " | Repeticiones: " + re.getRepetitions() +
                        " | Descanso: " + re.getRest() + "s");
            }

            System.out.println("\n3. Consultando la asociación por ID (" + idGenerado + ")...");
            RoutineExcercise encontrado = routineExerciseRepository.findById(idGenerado);
            if (encontrado != null) {
                System.out.println("-> Registro ubicado. Series: " + encontrado.getSeries() + " | Repeticiones: " + encontrado.getRepetitions());
            }

            System.out.println("\n4. Modificando volumen de trabajo en la asociación...");
            if (encontrado != null) {
                encontrado.setSeries(5);
                encontrado.setRepetitions(10);
                encontrado.setRest(90);

                routineExerciseRepository.update(encontrado);

                RoutineExcercise actualizado = routineExerciseRepository.findById(idGenerado);
                System.out.println("-> Cambios aplicados. Nuevas Series: " + actualizado.getSeries() +
                        " | Repeticiones: " + actualizado.getRepetitions() +
                        " | Descanso: " + actualizado.getRest() + "s");
            }

            System.out.println("\n5. Eliminando el vínculo entre ejercicio y rutina...");
            routineExerciseRepository.deleteById(idGenerado);

            RoutineExcercise eliminado = routineExerciseRepository.findById(idGenerado);
            if (eliminado == null) {
                System.out.println("-> Verificación: Asociación removida.");
            }

            excerciseRepository.deleteById(excercise.getIdExercise());
            routineRepository.deleteById(routine.getIdRoutine());
            userRepository.deleteById(user.getIdUser());

            System.out.println("\n========== PROCESO DE EVALUACIÓN FINALIZADO ==========");

        } catch (Exception e) {
            System.err.println("Excepción durante pruebas de RoutineExerciseRepository: " + e.getMessage());
            e.printStackTrace();
        }
    }
}