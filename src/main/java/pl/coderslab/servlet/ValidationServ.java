package pl.coderslab.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet(name = "abc ", urlPatterns = "/validation")
public class ValidationServ extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList al = new ArrayList();
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String sno1 = req.getParameter("validpro_sno");
        String sname = req.getParameter("validpro_sname");
        String smarks1 = req.getParameter("validpro_smarks");
        int sno = 0;
        float smarks = 0;
        if ((sno1 == null) || (sno1.equals(""))) {
            al.add("PROVIDE STUDENT NUMBER...");
        } else {
            try {
                sno = Integer.parseInt("sno1");
            } catch (NumberFormatException nfe) {
                al.add("PROVIDE int DATA IN STUDENT NUMBER...");
            }
        }
        if ((sname == null) || (sname.equals(""))) {
            al.add("PROVIDE STUDENT NAME...");
        }
        if ((smarks1 == null) || (smarks1.equals(""))) {
            al.add("PROVIDE STUDENT MARKS...");
        } else {
            try {
                smarks = Float.parseFloat("smarks1");
            } catch (NumberFormatException nfe) {
                al.add("PROVIDE float DATA IN STUDENT MARKS...");
            }
        }
        if (al.size() != 0) {
            pw.println(al);
        } else {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Hanuman", "scott", "tiger");
                PreparedStatement ps = con.prepareStatement("insert into Student values (?,?,?)");
                ps.setInt(1, sno);
                ps.setString(2, sname);
                ps.setFloat(3, smarks);
                int i = ps.executeUpdate();
                if (i > 0) {
                    pw.println("RECORD INSERTED...");
                } else {
                    pw.println("RECORD NOT INSERTED...");
                }
                con.close();
            } catch (Exception e) {
                res.sendError(503, "PROBLEM IN DATABASE...");
            }
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
};