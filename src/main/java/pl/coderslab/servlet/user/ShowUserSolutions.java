package pl.coderslab.servlet.user;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowUserSolutions", urlPatterns = "/user/showusersolution")
public class ShowUserSolutions extends HttpServlet {
    private List<User> userList = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();
    private SolutionDAO solutionDAO = new SolutionDAO();
    private User user = new User();
    private List<Solution> solutions = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        user = userDAO.read(id);
        solutions = solutionDAO.findAllByUserId(id);

        HttpSession session = request.getSession();
        session.setAttribute("userId", id);
        request.setAttribute("session", session);
        //Przekaż parametry
        request.setAttribute("user", user);
        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("/showUser.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userList = userDAO.findAll();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/userToShow.jsp").forward(request, response);

    }
}
