package pl.coderslab.servlet.solution;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddSolution", urlPatterns = "/solution/addsolution")
public class AddSolution extends HttpServlet {
    private SolutionDAO solutionDAO = new SolutionDAO();
    private UserDAO userDAO = new UserDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Solution solution = new Solution();
        solutionDAO.create(solution);

        request.setAttribute("message", "Rozwiązanie zostało utworzone");
        request.getRequestDispatcher("/addSolution.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);

        request.getRequestDispatcher("/addSolution.jsp").forward(request, response);
    }
}
