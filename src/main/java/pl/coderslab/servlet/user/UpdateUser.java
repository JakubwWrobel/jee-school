package pl.coderslab.servlet.user;

import com.mysql.cj.PreparedQuery;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@WebServlet(name = "UpdateUser", urlPatterns = "/updateuser")
public class UpdateUser extends HttpServlet {
    private static UserDAO userDAO = new UserDAO();
    private static User user;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email == null) {
            Integer userId = Integer.parseInt(request.getParameter("user"));
            user = userDAO.read(userId);
            request.setAttribute("u", user);
            RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/addUser2.jsp");
            requestDispatcher2.forward(request, response);


        }else if( email != null) {
            String userName = request.getParameter("userName");
            String emailU = request.getParameter("email");
            String password = request.getParameter("password");
            user.setPassword(password);
            user.setEmail(emailU);
            user.setUsername(userName);
            try {
                if (userDAO.update(user)) {
                    List<User> userList = userDAO.findAll();
                    request.setAttribute("user", userList);
                    request.setAttribute("messages", "Użytkownik został zaaktualizowany");
                    request.getRequestDispatcher("updateUser.jsp").forward(request, response);
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<User> userList = userDAO.findAll();
        request.setAttribute("users", userList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/updateUser.jsp");
        requestDispatcher.forward(request, response);
    }
}
