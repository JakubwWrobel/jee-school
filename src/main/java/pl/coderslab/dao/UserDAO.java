package pl.coderslab.dao;


import add.DbUtil;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ?, user_group_id = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";
    private static final String FIND_ALL_USERS_BY_USER_GROUP =
            "SELECT * FROM users WHERE user_group_id = ?";
    private static final String SELECT_PASSWORD =
            "SELECT password FROM users WHERE email = (SELECT email FROM users WHERE is_admin = 1)";
    private PreparedStatement statement;
    UserGroupDAO userGroupDAO = new UserGroupDAO();

    public User create(User user) throws SQLIntegrityConstraintViolationException {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();


            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                //DO SPRAWDZENIA:
                int userGroupId = resultSet.getInt("user_group_id");
                UserGroup userGroup = userGroupDAO.read(userGroupId);
                user.setUserGroup(userGroup);

                return user;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }


    //DOBRZE ZAIMPLEMENTOWAŁEM TUTAJ RZUCANIE WYJĄTKU SQLIntegrityConstraintViolationException??
    public boolean update(User user) throws SQLIntegrityConstraintViolationException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroup().getId());
            statement.setInt(5, user.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException();
        } catch (SQLException e) {
            System.out.println("Błąd połączenia");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            return false;
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[tmpUsers.length - 1] = u;
        return tmpUsers;
    }

    public List<User> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                //TEST OF READING FROM OTHER CLASS:
                int userGroupId = resultSet.getInt("user_group_id");
                UserGroup userGroup = userGroupDAO.read(userGroupId);
                user.setUserGroup(userGroup);

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Błąd połączenia z bazą");
            return null;
        }
    }


    public User[] findAllByGroupId(int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(FIND_ALL_USERS_BY_USER_GROUP);
            statement.setInt(1, userInput);
            ResultSet resultSet = statement.executeQuery();
            User[] users = new User[0];
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                if (resultSet.getInt("user_group_id") != 0) {
                    user.setUserGroup(userGroupDAO.read(resultSet.getInt("user_group_id")));
                } else {
                    user.setUserGroup(null);
                }
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            return null;
        }
    }

    public static String isAdmin() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_PASSWORD);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String passwordAdmin = resultSet.getString("password");
                return passwordAdmin;
            }
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            return null;
        }
        return null;
    }
}
