package com.sena.repository;

import com.sena.database.ConnectionDB;
import com.sena.model.RoutineExcercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoutineExerciseRepositoryImpl implements  RoutineExerciseRepository {

    @Override
    public RoutineExcercise save(RoutineExcercise routineExcercise) {

        String sql = "INSERT INTO routineExercises (idRoutine, idExercise, series, repetitions, rest) VALUES (?, ? ,?,?,?)";

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setLong(1, routineExcercise.getIdRoutine());
            statement.setLong(2, routineExcercise.getIdExercise());
            statement.setInt(3, routineExcercise.getSeries());
            statement.setInt(4, routineExcercise.getRepetitions());
            statement.setInt(5, routineExcercise.getRest());

            statement.executeUpdate();

            try(ResultSet generatedKeys= statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    routineExcercise.setIdRoutineExcercise(generatedKeys.getLong(1));
                }
            }

            return routineExcercise;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el nuevo registro " + e);
        }

    }

    @Override
    public List<RoutineExcercise> findAll() {

        String sql = "SELECT * FROM routineExercises";

        List<RoutineExcercise> allRoutineExcercises = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()) {
                RoutineExcercise routineEx = new RoutineExcercise();

                routineEx.setIdRoutineExcercise(resultSet.getLong("idRoutineExercise"));
                routineEx.setIdRoutine(resultSet.getLong("idRoutine"));
                routineEx.setIdExercise(resultSet.getLong("idExercise"));
                routineEx.setSeries(resultSet.getInt("series"));
                routineEx.setRepetitions(resultSet.getInt("repetitions"));
                routineEx.setRest(resultSet.getInt("rest"));

                allRoutineExcercises.add(routineEx);
            }

            return allRoutineExcercises;

        } catch (SQLException e) {
            throw new RuntimeException("Error en la busqueda de registros " + e);
        }
    }

    @Override
    public RoutineExcercise findById(Long id) {

        String sql = "SELECT * FROM routineExercises WHERE idRoutineExercise = ? ";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    RoutineExcercise routineExcercise = new RoutineExcercise();

                    routineExcercise.setIdRoutineExcercise(resultSet.getLong("idRoutineExercise"));
                    routineExcercise.setIdRoutine(resultSet.getLong("idRoutine"));
                    routineExcercise.setIdExercise(resultSet.getLong("idExercise"));
                    routineExcercise.setSeries(resultSet.getInt("series"));
                    routineExcercise.setRepetitions(resultSet.getInt("repetitions"));
                    routineExcercise.setRest(resultSet.getInt("rest"));

                    return routineExcercise;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda del registro " + e);
        }
    }

    @Override
    public RoutineExcercise update(RoutineExcercise routineExcercise) {

        String sql = "UPDATE routineExercises SET idRoutine = ? , idExercise = ? , series = ? , repetitions = ? , rest = ? WHERE idRoutineExercise = ?";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, routineExcercise.getIdRoutine());
            statement.setLong(2, routineExcercise.getIdExercise());
            statement.setInt(3, routineExcercise.getSeries());
            statement.setInt(4, routineExcercise.getRepetitions());
            statement.setInt(5, routineExcercise.getRest());
            statement.setLong(6, routineExcercise.getIdRoutineExcercise());

            statement.executeUpdate();

            return routineExcercise;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro " + e);
        }
    }

    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM routineExercises WHERE idRoutineExercise = ? ";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en la eliminación del registro" + e);
        }

    }
}
