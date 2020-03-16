package pl.coderslab.servlet.usergroup;

import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAllUsersInGroup", urlPatterns = "/usergroup/allusersingroup")
public class ShowAllUsersInGroup extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private List<User> users;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        users = userDAO.findAllByGroupId(id);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/allUsersInGroup.jsp").forward(request, response);

    }
}
