package pl.coderslab.servlet.usergroup;

import pl.coderslab.dao.UserGroupDAO;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAllGroups", urlPatterns = "/usergroup/showallgroups" )
public class ShowAllGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserGroup> userGroups = UserGroupDAO.showAll();
            request.setAttribute("groups", userGroups);
        request.getRequestDispatcher("/showAllGroups.jsp").forward(request, response);
    }
}
