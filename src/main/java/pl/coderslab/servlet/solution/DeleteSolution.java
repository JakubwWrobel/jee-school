package pl.coderslab.servlet.solution;

import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteSolution", urlPatterns = "/solution/deletesolution")
public class DeleteSolution extends HttpServlet {
    private SolutionDAO solutionDAO = new SolutionDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer solutionId = Integer.parseInt(request.getParameter("solution"));
        solutionDAO.delete(solutionId);
        List<Solution> solutions = solutionDAO.findAll();
        request.setAttribute("solutions", solutions);
        request.setAttribute("message", "Rozwiązanie zostało usunięte");
        request.getRequestDispatcher("/deleteSolution.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Solution> solutions = solutionDAO.findAll();
        request.setAttribute("solutions", solutions);

        request.getRequestDispatcher("/deleteSolution.jsp").forward(request, response);
    }
}
