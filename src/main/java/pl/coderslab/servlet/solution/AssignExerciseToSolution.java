package pl.coderslab.servlet.solution;

import com.sun.mail.imap.protocol.INTERNALDATE;
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
    private  SolutionDAO solutionDAO = new SolutionDAO();
    private  ExerciseDAO exerciseDAO = new ExerciseDAO();
    private  List<Solution> listOfSolutions;
    private  List<Exercise> listOfExercises;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
        Integer solutionId = Integer.parseInt(request.getParameter("solutionId"));

        Solution solution = solutionDAO.read(solutionId);
        solutionDAO.insertExerciseIntoSolution(solution,exerciseId);
        request.setAttribute("message", "Zadanie zostało przypisane do rozwiązania");

        listOfSolutions = solutionDAO.findAll();
        listOfExercises = exerciseDAO.findAll();
        request.setAttribute("listOfSolutions", listOfSolutions);
        request.setAttribute("listOfExercises", listOfExercises);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/assignExerciseToSolution.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listOfSolutions = solutionDAO.findAll();
        listOfExercises = exerciseDAO.findAll();
        request.setAttribute("listOfSolutions", listOfSolutions);
        request.setAttribute("listOfExercises", listOfExercises);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/assignExerciseToSolution.jsp");
        dispatcher.forward(request, response);
    }
}
