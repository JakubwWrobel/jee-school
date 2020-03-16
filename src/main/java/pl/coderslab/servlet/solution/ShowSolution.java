package pl.coderslab.servlet.solution;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowSolution", urlPatterns = "/solution/showsolution")
public class ShowSolution extends HttpServlet {
    private SolutionDAO solutionDAO = new SolutionDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Solution solution = solutionDAO.read(id);
        request.setAttribute("solution", solution);
        request.getRequestDispatcher("/showSolution.jsp").forward(request, response);
    }
}
