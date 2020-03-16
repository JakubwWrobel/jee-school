package pl.coderslab.servlet.solution;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AssignUserToExercise", urlPatterns = "/solution/assignusertoexercise")
public class AssignUserToExercise extends HttpServlet {
    private SolutionDAO solutionDAO = new SolutionDAO();
    private UserDAO userDAO = new UserDAO();
    private User user = new User();
    private List<Solution> solutions;
    private List<User> users;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer solutionId = Integer.parseInt(request.getParameter("solutionId"));

        user = userDAO.read(userId);
        solutionDAO.assignSolutionToTheUser(user, solutionId);

        //Repeat
        users = userDAO.findAll();
        solutions = solutionDAO.findAll();
        request.setAttribute("message", "Zadanie zostało przypisane do użytkownika");
        request.setAttribute("users", users);
        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("/assignUserToExercise.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        users = userDAO.findAll();
        solutions = solutionDAO.findAll();
        request.setAttribute("users", users);
        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("/assignUserToExercise.jsp").forward(request, response);
    }
}