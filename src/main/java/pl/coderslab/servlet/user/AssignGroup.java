package pl.coderslab.servlet.user;

import pl.coderslab.dao.UserDAO;
import pl.coderslab.dao.UserGroupDAO;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "assignGroup", urlPatterns = "/assignGroup")
public class AssignGroup extends HttpServlet {
    private  User user;
    private  UserGroup userGroup;
    private  UserGroupDAO userGroupDAO = new UserGroupDAO();
    private  List<UserGroup> listOfGroups = userGroupDAO.showAll();
    private  UserDAO userDAO = new UserDAO();
    private  List<User> listOfUsers = userDAO.findAll();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Information about User Group and User
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer userGroupId = Integer.parseInt(request.getParameter("groupId"));

        //Reading
        user = userDAO.read(userId);

        //Updating
        user = userGroupDAO.insertUserGroupToUser(user, userGroupId);
        //Comeback to jsp
        request.setAttribute("listOfGroups", listOfGroups);
        request.setAttribute("listOfUsers", listOfUsers);
        if (user != null) {
            request.setAttribute("message", "Przypisano grupę do użytkownika");
            request.getRequestDispatcher("assignGroup.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listOfGroups", listOfGroups);
        request.setAttribute("listOfUsers", listOfUsers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("assignGroup.jsp");
        dispatcher.forward(request, response);
    }
}
