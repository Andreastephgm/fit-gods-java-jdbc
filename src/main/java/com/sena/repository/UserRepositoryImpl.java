package com.sena.repository;

import com.sena.database.ConnectionDB;
import com.sena.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User save(User user) {

        String sql = """
                INSERT INTO users(
                name, surname, email, password, dateOfBirth, weight, height, objective)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());

            if (user.getDateOfBirth() != null) {
                statement.setDate(5,
                        new java.sql.Date(user.getDateOfBirth().getTime()));
            } else {
                statement.setDate(5, null);
            }

            if (user.getWeight() != null) {
                statement.setDouble(6, user.getWeight());
            } else {
                statement.setNull(6, Types.DECIMAL);
            }

            if (user.getHeight() != null) {
                statement.setDouble(7, user.getHeight());
            } else {
                statement.setNull(7, Types.DECIMAL);
            }

            statement.setString(8, user.getObjective());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setIdUser(generatedKeys.getLong(1));
                }
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el usuario", e);
        }
    }

    @Override
    public List<User> findAll() {

        String sql = "SELECT * FROM users";

        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setIdUser(resultSet.getLong("id_user"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                user.setWeight(resultSet.getDouble("weight"));
                user.setHeight(resultSet.getDouble("height"));
                user.setObjective(resultSet.getString("objective"));

                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar los usuarios", e);
        }
    }

    @Override
    public User findById(Long id) {

        String sql = "SELECT * FROM users WHERE id_user = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    User user = new User();

                    user.setIdUser(resultSet.getLong("id_user"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                    user.setWeight(resultSet.getDouble("weight"));
                    user.setHeight(resultSet.getDouble("height"));
                    user.setObjective(resultSet.getString("objective"));

                    return user;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el usuario", e);
        }
    }

    @Override
    public User update(User user) {

        String sql = """
                UPDATE users
                SET name = ?,
                    surname = ?,
                    email = ?,
                    password = ?,
                    dateOfBirth = ?,
                    weight = ?,
                    height = ?,
                    objective = ?
                WHERE id_user = ?
                """;

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());

            if (user.getDateOfBirth() != null) {
                statement.setDate(5,
                        new java.sql.Date(user.getDateOfBirth().getTime()));
            } else {
                statement.setDate(5, null);
            }

            if (user.getWeight() != null) {
                statement.setDouble(6, user.getWeight());
            } else {
                statement.setNull(6, Types.DECIMAL);
            }

            if (user.getHeight() != null) {
                statement.setDouble(7, user.getHeight());
            } else {
                statement.setNull(7, Types.DECIMAL);
            }

            statement.setString(8, user.getObjective());
            statement.setLong(9, user.getIdUser());

            statement.executeUpdate();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM users WHERE id_user = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el usuario", e);
        }
    }
}