package pl.coderslab.servlet.exercise;

import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreateExercise", urlPatterns = "/exercise/addexercise")
public class AddExercise extends HttpServlet {
    private ExerciseDAO exerciseDAO = new ExerciseDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exercisename = request.getParameter("exercisename");
        String exercisedesc = request.getParameter("exercisedesc");
        Map<String, String> messages = new HashMap<>();

        if (exercisename.trim().isEmpty() || exercisename == null) {
            messages.put("exercise", "Wartość nie może być pusta");
        }

        if (messages.isEmpty()) {
            Exercise exercise = new Exercise();
            exercise.setTitle(exercisename);
            exercise.setDescription(exercisedesc);
            exerciseDAO.create(exercise);
            messages.put("success", "Zadanie zostało dodane");
        }
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/addExercise.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/addExercise.jsp").forward(request, response);
    }
}
