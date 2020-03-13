package pl.coderslab;

import add.DbUtil;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Test {
    static UserDAO userDAO = new UserDAO();
    public static void main(String[] args) throws SQLIntegrityConstraintViolationException {
        try (Connection conn = DbUtil.getConnection()) {

        } catch (SQLException e) {
            e.getErrorCode();
            System.out.println("Błąd połączenia z bazą");
        }
    }
}
