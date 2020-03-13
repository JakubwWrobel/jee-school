package add;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.InputMismatchException;
public class Checking {
    private static Scanner scanner = new Scanner(System.in);




    public static String checkingString(String str) {
        boolean running = true;

        while (running) {
            if (str.isEmpty() || str == null) {
                System.out.println("Podana wartość nie może być pusta");
                str = scanner.nextLine();
            } else {
                return str;
            }
        }
        return null;
    }

    public static String isValidEmailAddress(String email) {
        boolean running = true;

        while (running) {
            try {
                if (email.isEmpty() || email == null) {
                    System.out.println("Podana wartość nie może być pusta");
                    email = scanner.nextLine();
                } else {
                    InternetAddress emailAddr = new InternetAddress(email);
                    emailAddr.validate();
                    running = false;
                    return email;
                }
            } catch (AddressException ex) {
                System.out.println("To nie jest poprawny format pliku mail");
                email = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Well... ");
            }
        }
        return null;
    }

    public static int checkingInt(){
//CHECKING IF INPUT IS AN INTEGER AND IS NOT EMPTY
        while(!scanner.hasNextInt() || scanner.findInLine("(?=\\S) ") != null){
            System.out.println("ID jest liczbą, podaj poprawne ID");
            scanner.next();
        }
        int id = scanner.nextInt();
        return id;
    }

}

