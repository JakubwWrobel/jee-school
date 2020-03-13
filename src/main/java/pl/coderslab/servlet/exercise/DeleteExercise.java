package pl.coderslab.servlet.exercise;

import com.sun.mail.imap.protocol.INTERNALDATE;
import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.model.Exercise;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DeleteExercise", urlPatterns = "/exercise/deleteexercise")
public class DeleteExercise extends HttpServlet {
    private ExerciseDAO exerciseDAO = new ExerciseDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer exerciseID = Integer.parseInt(request.getParameter("exercise"));

        if (exerciseDAO.delete(exerciseID)) {
            List<Exercise> exercises = exerciseDAO.findAll();
            request.setAttribute("exercises", exercises);
            request.setAttribute("message", "Zadanie zostało usunięte");
            request.getRequestDispatcher("/deleteExercise.jsp").forward(request, response);
        } else {
            List<Exercise> exercises = exerciseDAO.findAll();
            request.setAttribute("exercises", exercises);
            request.setAttribute("message", "Istniejące rozwiązanie jest przypisane do tego zadania");
            request.getRequestDispatcher("/deleteExercise.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        List<Exercise> exercises = exerciseDAO.findAll();
        request.setAttribute("exercises", exercises);
        request.getRequestDispatcher("/deleteExercise.jsp").forward(request, response);
    }
}
