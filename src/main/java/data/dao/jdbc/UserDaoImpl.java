package data.dao.jdbc;

import data.dao.IUserDao;
import data.model.User;
import exceptions.EntityNotFoundException;
import util.SqlJdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = "";


    /* Basic CRUD operations */
    @Override
    public User selectEntityById(int id) throws SQLException {
        sql = "SELECT * FROM users WHERE id=?";
        User user = new User();
        try (Connection connection = SqlJdbcUtil.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setDateOfBirth(resultSet.getString("dateOfBirth"));
                user.setPassword(resultSet.getString("password"));
            } else {
                throw new EntityNotFoundException("User with ID '" + id + "' not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closePreparedStatement().closeResultSet();
        }
        return user;
    }

    @Override
    public List<User> selectAllEntity() throws SQLException {
        sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = SqlJdbcUtil.getConnection()) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setDateOfBirth(resultSet.getString("dateOfBirth"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatement().closeResultSet();
        }
        return users;
    }

    @Override
    public void insertEntity(User entity) throws SQLException {
        sql = "INSERT INTO users(firstName, lastName, phone, email, dateOfBirth, password) values(?, ?, ?, ?, CONVERT(DATE, ?, 103), ?)";
        try (Connection connection = SqlJdbcUtil.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getDateOfBirth());
            preparedStatement.setString(6, entity.getPassword());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted == 1) {
                connection.commit();
                System.out.println("New user successfully inserted");
            } else {
                connection.rollback();
                System.out.println("Insertion failed, Rolling back transaction");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closePreparedStatement();
        }
    }

    @Override
    public void updateEntityById(User entity, int id) throws SQLException {
        sql = "UPDATE users SET firstName=?, lastName=?, phone=?, email=?, dateOfBirth=?, password=? WHERE id=?";
        try (Connection connection = SqlJdbcUtil.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getDateOfBirth());
            preparedStatement.setString(6, entity.getPassword());
            preparedStatement.setInt(7, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows == 1) {
                connection.commit();
                System.out.println("User updated successfully");
            } else {
                connection.rollback();
                throw new EntityNotFoundException("User with ID '" + id + "' not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closePreparedStatement();
        }
    }

    @Override
    public void deleteEntityById(int id) throws SQLException {
        sql = "DELETE FROM users WHERE id=?";
        try (Connection connection = SqlJdbcUtil.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 1) {
                connection.commit();
                System.out.println("Row with ID: '" + id + "' deleted successfully");
            } else {
                connection.rollback();
                throw new EntityNotFoundException("User with ID '" + id + "' not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closePreparedStatement();
        }
    }

    /* Specific operations */
    public int insertUser(User entity) throws SQLException {
        int generatedKey = 0;
        sql = "INSERT INTO users(firstName, lastName, phone, email, dateOfBirth, password) values(?, ?, ?, ?, CONVERT(DATE, ?, 103) , ?)";
        try (Connection connection = SqlJdbcUtil.getConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getDateOfBirth());
            preparedStatement.setString(6, entity.getPassword());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedKey = generatedKeys.getInt(1);
                    connection.commit();
                    System.out.println("New user successfully inserted with ID: " + generatedKey);
                } else {
                    connection.rollback();
                    System.out.println("Insertion failed, no ID obtained. Rolling back transaction");
                }
            } else {
                connection.rollback();
                System.out.println("Insertion failed, Rolling back transaction");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closePreparedStatement();
        }
        return generatedKey;
    }

    public User selectEntityByIdWithSpecificDateFormat(int id) throws SQLException {
        sql = "SELECT firstName, lastName, phone, email, FORMAT(dateOfBirth, 'MM/dd/yyyy') AS dateOfBirth, password FROM users WHERE id=?";
        User user = new User();
        try (Connection connection = SqlJdbcUtil.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setDateOfBirth(resultSet.getString("dateOfBirth"));
                user.setPassword(resultSet.getString("password"));
            } else {
                throw new EntityNotFoundException("User with ID '" + id + "' not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closePreparedStatement().closeResultSet();
        }
        return user;
    }


    /* Other */
    private UserDaoImpl closeResultSet() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        return this;
    }

    private UserDaoImpl closeStatement() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        return this;
    }

    private UserDaoImpl closePreparedStatement() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        return this;
    }


}
