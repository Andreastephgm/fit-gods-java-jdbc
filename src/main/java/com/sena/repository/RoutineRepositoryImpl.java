package com.sena.repository;

import com.sena.database.ConnectionDB;
import com.sena.model.Routine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoutineRepositoryImpl implements  RoutineRepository {

    @Override
    public Routine save(Routine routine) {
        String sql = """
                INSERT INTO routines(name,objective, idUser)
                VALUES(?,?,?)
                """;
        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, routine.getName());
            statement.setString(2, routine.getObjective());
            statement.setLong(3, routine.getIdUser());

            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    routine.setIdRoutine((generatedKeys.getLong(1)));
                }
            }
            return routine;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la rutina",e);
        }

    }

    @Override
    public List<Routine> findAll() {

        String sql = "SELECT * FROM routines";

        List<Routine> routines = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                Routine routine = new Routine();

                routine.setIdRoutine(resultSet.getLong("idRoutine"));
                routine.setName(resultSet.getString("name"));
                routine.setObjective(resultSet.getString("objective"));
                routine.setIdUser(resultSet.getLong("idUser"));

                routines.add(routine);
            }

            return routines;

        }catch(SQLException e){
            throw  new RuntimeException("Error al consultar rutinas " , e);
        }
    }

    @Override
    public Routine findById(Long id) {

        String sql = "SELECT * FROM routines WHERE idRoutine = ?";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()){

                if(resultSet.next()){

                    Routine routine = new Routine();

                    routine.setIdRoutine(resultSet.getLong("idRoutine"));
                    routine.setName(resultSet.getString("name"));
                    routine.setObjective(resultSet.getString("objective"));
                    routine.setIdUser(resultSet.getLong("idUser"));

                    return routine;
                }
            }

            return null;

        }catch(SQLException e){
            throw new RuntimeException("Error al buscar la rutina", e);
        }
    }

    @Override
    public Routine update(Routine routine) {
        String sql = """
                UPDATE routines SET name = ? , objective = ? WHERE idRoutine = ? 
                """;

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, routine.getName());
            statement.setString(2, routine.getObjective());
            statement.setLong(3, routine.getIdRoutine());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Error de actualización de la rutina " + e);
        }
        return routine;
    }

    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM routines WHERE idRoutine = ?";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error: No se pudo borrar la rutina " + e);
        }
    }
}
