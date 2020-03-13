package pl.coderslab.servlet.exercise;

import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateExercise", urlPatterns = "/exercise/updateexercise")
public class UpdateExercise extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private static Integer exerciseId;
    private ExerciseDAO exerciseDAO = new ExerciseDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exerciseName = request.getParameter("exerciseName");
        String exerciseDesc = request.getParameter("exerciseDesc");


        if (exerciseName == null) {
            exerciseId = Integer.parseInt(request.getParameter("exercise"));
            Exercise exercise = exerciseDAO.read(exerciseId);
            request.setAttribute("exercises", exercise);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/updateExerciseFinal.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Exercise exercise = new Exercise();
            exercise.setTitle(exerciseName);
            exercise.setDescription(exerciseDesc);
            exercise.setId(exerciseId);

            exerciseDAO.update(exercise);
            request.setAttribute("message", "Grupa została zaaktualizowana");
            request.getRequestDispatcher("/updateExercise.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Exercise> exerciseList = exerciseDAO.findAll();
        request.setAttribute("exercises", exerciseList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/updateExercise.jsp");
        dispatcher.forward(request, response);
    }
}
