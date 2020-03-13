package pl.coderslab.servlet.usergroup;

import pl.coderslab.dao.UserGroupDAO;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateUserGroup", urlPatterns = "/usergroup/updateusergroup")
public class UpdateUserGroup extends HttpServlet {
    private List<UserGroup> groups;
    private UserGroupDAO userGroupDAO = new UserGroupDAO();
    private static Integer groupId;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = request.getParameter("groupName");


        if (groupName == null) {
            groupId = Integer.parseInt(request.getParameter("group"));
            UserGroup userGroup = userGroupDAO.read(groupId);
            request.setAttribute("usergroup", userGroup);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/updateGroupFinal.jsp");
            requestDispatcher.forward(request, response);
        } else {
            UserGroup userGroup = new UserGroup();
            userGroup.setUserGroupName(groupName);
            userGroup.setId(groupId);
            userGroupDAO.update(userGroup);
            request.setAttribute("messages", "Grupa została zaaktualizowany");
            request.getRequestDispatcher("/updateGroup.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        groups = UserGroupDAO.showAll();
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("/updateGroup.jsp").forward(request, response);
    }
}
