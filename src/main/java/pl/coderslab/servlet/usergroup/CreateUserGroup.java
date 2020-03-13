package pl.coderslab.servlet.usergroup;

import pl.coderslab.dao.UserGroupDAO;
import pl.coderslab.model.UserGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CreateUserGroup", urlPatterns = "/usergroup/creategroup")
public class CreateUserGroup extends HttpServlet {
    private UserGroupDAO userGroupDAO = new UserGroupDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);

        String groupName = request.getParameter("groupname");

        if(groupName == null || groupName.trim().isEmpty()){
            messages.put("groupname", "Wartość nie może być pusta");
            System.out.println("test");
        }

        if(messages.isEmpty()){
            UserGroup userGroup = new UserGroup();
            userGroup.setUserGroupName(groupName);
            userGroupDAO.create(userGroup);
            messages.put("success", "Grupa została utworzona");

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/addGroup.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addGroup.jsp").forward(request, response);
    }
}
