package pl.coderslab.servlet;

import add.Checking;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.dao.UserGroupDAO;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddUser", urlPatterns = "/adduser")
public class AddUser extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    UserGroupDAO userGroupDAO = new UserGroupDAO();
    private static String reg1 = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}";
    Pattern pattern = Pattern.compile(reg1);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Preparing messages
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);

        //casting parameters
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userGroup = request.getParameter("userGroup");
        Matcher matcher = pattern.matcher(email);

        //validating
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("userName", "Please enter name");
        }

        if (email == null || email.trim().isEmpty()) {
            messages.put("email", "Please enter your email");
        } else if (!matcher.matches()) {
            messages.put("email", "Please enter correct email format");
        }
        if (password == null || password.trim().isEmpty()) {
            messages.put("password", "Provide password");
        }

        if (messages.isEmpty()) {
            try {
                User user = new User(userName, email, password);
                userDAO.create(user);
                messages.put("success", "Użytkownik został dodany");

            } catch (SQLIntegrityConstraintViolationException e) {
                messages.put("emailExists", "Podany email już istnieje w bazie");
            }
        }

        List<UserGroup> listOfGroups = userGroupDAO.showAll();
        request.setAttribute("listOfGroups", listOfGroups);

        RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserGroup> listOfGroups = userGroupDAO.showAll();
        request.setAttribute("listOfGroups", listOfGroups);

        RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
        dispatcher.forward(request, response);



    }
}


/*
    protected static void addUser() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Podaj nazwę użytkownika ");
            String userName = Checking.checkingString(scanner.nextLine());

            System.out.println("Podaj email użytkownika ");
            String email = Checking.isValidEmailAddress(scanner.nextLine());

            System.out.println("Podaj hasło użytkownika ");
            String password = Checking.checkingString(scanner.nextLine());

            User u = new User(userName, email, password);
            try {
                userDAO.create(u);
                System.out.println("Chcesz przypisać użytkownika do grupy?");
                System.out.println("Yes/No");
                String userGroup = Checking.checkingString(scanner.nextLine()).toLowerCase();
                if (userGroup.equals("yes")) {
                    while (running) {
                        System.out.println("Podaj ID grupy");
                        userGroupDAO.showAll();
                        UserGroup userGroup1 = userGroupDAO.read(Checking.checkingInt());
                        if (userGroup1 == null) {
                            System.out.println("Podana grupa nie istnieje");
                        } else {
                            int userInput = userGroup1.getUserGroupId();
                            userGroupDAO.insertUserGroupToUser(u, userInput);
                            running = false;
                        }
                    }
                }
                running = false;
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Podany email jest już wykorzystany");
            }
        }
        System.out.println("Użytkownik został dodany");
    }

        */