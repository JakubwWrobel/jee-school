package pl.coderslab.servlet.user;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowUser", urlPatterns = "/user/showuser")
public class ShowUser extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private SolutionDAO solutionDAO = new SolutionDAO();
    private User user = new User();
    private List<Solution> solutions = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer userId = Integer.parseInt(request.getParameter("id"));
        user = userDAO.read(userId);



        solutions = solutionDAO.findAllByUserId(userId);
        //Przekaż parametry
        request.setAttribute("user", user);
        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("/showUser.jsp").forward(request, response);


    }
}
