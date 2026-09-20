package com.sena.repository;

import com.sena.database.ConnectionDB;
import com.sena.model.Excercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcerciseRepositoryImpl implements  ExcerciseRepository{
    @Override
    public Excercise save(Excercise excercise) {
        String sql = """
                INSERT INTO excercises (name, description, muscularGroup) VALUES (?,?,?)
                """;

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, excercise.getName());
            statement.setString(2, excercise.getDescription());
            statement.setString(3, excercise.getMuscular_group());

            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    excercise.setId_excercise(generatedKeys.getLong(1));
                }
            }

            return excercise;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el ejercicio " + e);
        }
    }

    @Override
    public List<Excercise> findAll() {

        String sql = "SELECT * FROM excercises";
        List<Excercise> excercises = new ArrayList<>();

        try(Connection connection =  ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

           while(resultSet.next()){

               Excercise excercise  = new Excercise();
               excercise.setId_excercise(resultSet.getLong("exc"));
               excercise.setName(resultSet.getString("name"));
               excercise.setDescription(resultSet.getString("description"));
               excercise.setMuscular_group(resultSet.getString("muscularGroup"));

               excercises.add(excercise);
           }

            return excercises;

        }catch (SQLException e) {
            throw new RuntimeException("Error al consultar ejercicios" + e);
        }
    }

    @Override
    public Excercise findById(Long id) {

        String sql = "SELECT * FROM excercises WHERE idExcercise = ?";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setLong(1, id);

        try(ResultSet resultSet = statement.executeQuery()){

            if(resultSet.next()){
                Excercise excercise = new Excercise();

                excercise.setId_excercise(resultSet.getLong("idExcercise"));
                excercise.setName(resultSet.getString("name"));
                excercise.setDescription(resultSet.getString("description"));
                excercise.setMuscular_group(resultSet.getString("muscularGroup"));

                return excercise;
            }
        }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el ejercicio " + e);
        }

    }

    @Override
    public Excercise update(Excercise excercise) {

        String sql = "UPDATE excercises SET name = ? , description = ? , muscularGroup = ? WHERE idExcercise = ?";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, excercise.getName());
            statement.setString(2, excercise.getDescription());
            statement.setString(3, excercise.getMuscular_group());
            statement.setLong(4, excercise.getId_excercise());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el ejercicio " + e);
        }
        return excercise;
    }

    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM excercises WHERE idExcercise = ? ";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar registro " + e);
        }

    }
}
