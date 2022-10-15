package unina.webrtc.servlet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class SubscriptionServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        out.println("Subscription Servlet");
        out.println();

        out.println("Method doPost() invoked ...");

        out.println("Here is the list of parameters sent with the request:");
        out.println();

        out.println("Account data:");
        out.println();
        out.println("Username " + request.getParameter("username"));
        out.println("Password " + request.getParameter("password"));
        out.println();

        out.println("User data:");
        out.println();
        out.println("First name " + request.getParameter("name"));
        out.println("Last name " + request.getParameter("surname"));
        out.println("Nickname " + request.getParameter("nickname"));
        out.println("Email " + request.getParameter("email"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        out.println("Subscription Servlet");
        out.println();

        out.println("Sorry! This servlet does not support the doGet() method ...");
    }
}
