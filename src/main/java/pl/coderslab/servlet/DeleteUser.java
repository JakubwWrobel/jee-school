package pl.coderslab.servlet;

import com.sun.mail.imap.protocol.INTERNALDATE;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.dao.UserGroupDAO;
import pl.coderslab.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteUser", urlPatterns = "/deleteuser")
public class DeleteUser extends HttpServlet {
    private static UserDAO userDAO = new UserDAO();
    private static User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer userId = Integer.parseInt(request.getParameter("user"));
        user = userDAO.read(userId);
        if(userDAO.delete(user)){
            List<User> userList = userDAO.findAll();
            request.setAttribute("userList", userList);
            request.setAttribute("message", "Użytkownik został usunięty");
            request.getRequestDispatcher("deleteUser.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> userList = userDAO.findAll();
        request.setAttribute("userList", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteUser.jsp");
        dispatcher.forward(request, response);

    }
}
