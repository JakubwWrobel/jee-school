package pl.coderslab.servlet.user;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ResolveExercise", urlPatterns = "/user/resolveexercise")
public class ResolveExercise extends HttpServlet {
    private SolutionDAO solutionDAO = new SolutionDAO();
    private List<Solution> solutions = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();
    private Solution solution;
    private Integer userId;
    private Integer id;
    private String description;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        description = request.getParameter("description");

        HttpSession session = request.getSession();
        id = (Integer) session.getAttribute("id");
        userId = (Integer) session.getAttribute("userId");

        User user = userDAO.read(userId);
        solution = solutionDAO.read(id);

        solutionDAO.resolveExercise(user, description, solution);

        solutions = solutionDAO.findAllByUserId(userId);
        request.setAttribute("user", user);
        request.setAttribute("solutions", solutions);

        request.setAttribute("message", "Rozwiązanie zostało dodane");
        request.getRequestDispatcher("/showUser.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        request.setAttribute("session", session);


        request.getRequestDispatcher("/resolveExercise.jsp").forward(request, response);

    }
}
