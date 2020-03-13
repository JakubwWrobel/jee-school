package pl.coderslab.servlet;


import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.model.Solution;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet", urlPatterns = "/mainservlet")
public class MainServlet extends HttpServlet {
    private int numberSolutions;
    SolutionDAO solutionDAO = new SolutionDAO();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.numberSolutions = 5;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Solution> solutions = solutionDAO.findRecent(this.numberSolutions);
        request.setAttribute("solutions", solutions);

        request.getRequestDispatcher("/userPage.jsp").forward(request, response);
    }
}