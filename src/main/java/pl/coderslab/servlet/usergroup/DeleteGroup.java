package pl.coderslab.servlet.usergroup;

import pl.coderslab.dao.UserGroupDAO;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteGroup", urlPatterns = "/usergroup/deleteusergroup")
public class DeleteGroup extends HttpServlet {
    private UserGroupDAO userGroupDAO = new UserGroupDAO();
    private List<UserGroup> groups;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupId = Integer.parseInt(request.getParameter("group"));
        if (userGroupDAO.delete(groupId)) {
            groups = UserGroupDAO.showAll();
            request.setAttribute("groups", groups);
            request.setAttribute("message", "Grupa została usunięta");
            request.getRequestDispatcher("/deleteGroup.jsp").forward(request, response);
        } else {
            groups = UserGroupDAO.showAll();
            request.setAttribute("groups", groups);
            request.setAttribute("message", "Istniejące użytkownik przypisany do tego zadania");
            request.getRequestDispatcher("/deleteGroup.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserGroup> groups = UserGroupDAO.showAll();
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("/deleteGroup.jsp").forward(request, response);
    }
}
