package unina.webrtc.servlet.servlets;

import unina.webrtc.servlet.classes.Account;
import unina.webrtc.servlet.classes.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class SubscriptionServlet1 extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        out.println("Subscription Servlet");
        out.println();

        out.println("Method doPost() invoked ...");

        out.println("Here is the list of parameters sent with the form:");
        out.println();

        out.println("Account data:");
        out.println();
        out.println("Username " + username);
        out.println("Password " + password);
        out.println();

        out.println("User data:");
        out.println("First name " + name);
        out.println("Last name " + surname);
        out.println("Nickname " + nickname);
        out.println("Email " + email);

        Account account = new Account(username, password);
        User user = new User(name, surname, nickname, email);
        account.setOwner(user);

        System.out.println("Account object:");
        System.out.println("Username: " + account.getUsername());
        System.out.println("Password: " + account.getPassword());
        System.out.println("Owner: " + account.getOwner());
        System.out.println();

        System.out.println("User object:");
        System.out.println("First name: " + user.getName());
        System.out.println("Last name: " + user.getSurname());
        System.out.println("Nickname: " + user.getNickname());
        System.out.println("Email: " + user.getEmail());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        out.println("Subscription Servlet");
        out.println();

        out.println("Sorry! This servlet does not support the doGet() method...");
    }
}
