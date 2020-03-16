package pl.coderslab.servlet.user;

import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAllUsers", urlPatterns = "/showallusers")
public class ShowAllUsers extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private List<User> users;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        users = userDAO.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/showAllUsers.jsp").forward(request, response);
    }
}
