package com.sena.repository;

import com.sena.database.ConnectionDB;
import com.sena.model.Progress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressRepositoryImpl implements  ProgressRepository{
    @Override
    public Progress save(Progress progress) {

        String sql = """
                INSERT INTO progress (date, weight, notes, idUser) VALUES (?,?,?,?)
                """;

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            if(progress.getDate() != null){
                statement.setDate(1, java.sql.Date.valueOf(progress.getDate()));
            }else{
                statement.setNull(1, Types.DATE);
            }

            statement.setDouble(2, progress.getWeight());
            statement.setString(3, progress.getNotes());
            statement.setLong(4, progress.getIdUser());

            statement.executeUpdate();

            try(ResultSet generatedKeys= statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    progress.setIdProgress(generatedKeys.getLong(1));
                }
            }

            return progress;

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el progreso" + e);
        }
    }

    @Override
    public List<Progress> findAll() {

        String sql = "SELECT * FROM progress";

        List<Progress> allProgress = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){

                Progress progress = new Progress();

                progress.setIdProgress(resultSet.getLong("idProgress"));

                Date dbDate = resultSet.getDate("date");

                if(dbDate != null){
                    progress.setDate(dbDate.toLocalDate());
                }
                progress.setWeight(resultSet.getDouble("weight"));
                progress.setNotes(resultSet.getString("notes"));
                progress.setIdUser(resultSet.getLong("idUser"));

                allProgress.add(progress);
            }

            return  allProgress;

        } catch (SQLException e) {
            throw new RuntimeException("Error al mostar la lista de registros " + e);
        }
    }

    @Override
    public Progress findById(Long id) {

        String sql = "SELECT * FROM progress WHERE idProgress = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Progress progress = new Progress();

                    progress.setIdProgress(resultSet.getLong("idProgress"));

                    Date dbDate = resultSet.getDate("date");
                    if (dbDate != null) {
                        progress.setDate(dbDate.toLocalDate());
                    }

                    progress.setWeight(resultSet.getDouble("weight"));
                    progress.setNotes(resultSet.getString("notes"));
                    progress.setIdUser(resultSet.getLong("idUser"));

                    return progress;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al encontrar el registro de progreso " + e, e);
        }
    }

    @Override
    public Progress update(Progress progress) {

        String sql = "UPDATE progress SET date = ? , weight = ? , notes = ? WHERE idProgress = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (progress.getDate() != null) {
                statement.setDate(1, java.sql.Date.valueOf(progress.getDate()));
            } else {
                statement.setNull(1, Types.DATE);
            }

            statement.setDouble(2, progress.getWeight());
            statement.setString(3, progress.getNotes());
            statement.setLong(4, progress.getIdProgress());

            statement.executeUpdate();

            return progress;

        } catch (SQLException e) {
            throw new RuntimeException("Error en la actualización del registro de progreso " + e, e);
        }
    }

    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM progress WHERE idProgress = ? ";

        try(Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar el registro " + e);
        }
    }
}
