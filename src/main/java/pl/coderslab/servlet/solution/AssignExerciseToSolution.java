package pl.coderslab.servlet.solution;

import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.dao.SolutionDAO;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.UserGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AssignExerciseToSolution", urlPatterns = "/solution/assignexercisetosolution")
public class AssignExerciseToSolution extends HttpServlet {
    private static SolutionDAO solutionDAO = new SolutionDAO();
    private static ExerciseDAO exerciseDAO = new ExerciseDAO();
    private static List<Solution> listOfSolutions = solutionDAO.findAll();
    private static List<Exercise> listOfExercises = exerciseDAO.findAll();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listOfSolutions", listOfSolutions);
        request.setAttribute("listOfExercises", listOfExercises);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/assignexercisetosolution.jsp");
        dispatcher.forward(request, response);
    }
}
