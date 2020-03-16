package pl.coderslab.dao;



import add.DbUtil;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserGroupDAO {
    private static final String CREATE_USERGROUP_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";
    private static final String UPDATE_USERGROUP_QUERY =
            "UPDATE user_group SET name = ? WHERE id = ?";
    private static final String READ_USERGROUP_QUERY =
            "SELECT * FROM user_group WHERE id = ?";
    private static final String DELETE_USERGROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_USERGROUPS_QUERY =
            "SELECT * FROM user_group";
    private static final String UPDATE_USERGROUP_FOR_USER_QUERY =
            "UPDATE users SET user_group_id = ? WHERE id = ?";


    public User insertUserGroupToUser(User user, int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USERGROUP_FOR_USER_QUERY);
            statement.setInt(1, userInput);
            statement.setInt(2, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.getErrorCode();
            System.out.println("Błąd połączenia z bazą");
        }
        return user;
    }

    public UserGroup read(int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USERGROUP_QUERY);
            statement.setInt(1, userInput);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt("id"));
                userGroup.setUserGroupName(resultSet.getString("name"));
                return userGroup;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Podana groupa nie istnieje");
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }

    public UserGroup update(UserGroup userGroup) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USERGROUP_QUERY);
            statement.setInt(2, userGroup.getId());
            statement.setString(1, userGroup.getUserGroupName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd komunikacji z bazą danych");
            e.getErrorCode();
            return null;
        }
        return userGroup;
    }

    public UserGroup create(UserGroup userGroup) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USERGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userGroup.getUserGroupName());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                userGroup.setId(resultSet.getInt(1));
            }
            return userGroup;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            e.getErrorCode();
            return null;
        }
    }

    public boolean delete(int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USERGROUP_QUERY);
            statement.setInt(1, userInput);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            e.getErrorCode();
            return false;
        }
    }

    private UserGroup[] addToArray(UserGroup[] userGroups, UserGroup userGroup) {
        UserGroup[] tempUsersGroup = Arrays.copyOf(userGroups, userGroups.length + 1);
        tempUsersGroup[tempUsersGroup.length - 1] = userGroup;
        return tempUsersGroup;
    }

    public static List<UserGroup> showAll() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERGROUPS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            List<UserGroup> userGroups = new ArrayList<>();
            while (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt("id"));
                userGroup.setUserGroupName(resultSet.getString("name"));
                userGroups.add(userGroup);
            }
            return userGroups;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            e.getErrorCode();
            return null;
        }
    }
}



