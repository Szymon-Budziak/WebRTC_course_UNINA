package unina.webrtc.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Vector;

public class Validate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Vector<String> users = null;

    public Validate() {
        super();
        users = new Vector<String>();
        users.add("admin");
        users.add("custom_user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET!");
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST!");
        String targetId = request.getParameter("id");
        if (targetId == null || targetId.equals("")) {
            System.out.println("id parameter is missing ...");
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        System.out.println("Client has sent: " + targetId);
        if (users.contains(targetId)) {
            System.out.println("  -- " + targetId + " exists!");
            response.getWriter().append("<response>valid</response>");
        } else {
            System.out.println("  -- " + targetId + " does not exist!");
            response.getWriter().append("<response>invalid</response>");
        }
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
    }
}