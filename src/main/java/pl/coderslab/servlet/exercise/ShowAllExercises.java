package pl.coderslab.servlet.exercise;

import pl.coderslab.dao.ExerciseDAO;
import pl.coderslab.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAll", urlPatterns = "/exercise/showallexercises")
public class ShowAllExercises extends HttpServlet {
    private ExerciseDAO exerciseDAO = new ExerciseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Exercise> exerciseList = exerciseDAO.findAll();
        request.setAttribute("exercises", exerciseList);
        request.getRequestDispatcher("/showAllExercises.jsp").forward(request, response);
    }
}
