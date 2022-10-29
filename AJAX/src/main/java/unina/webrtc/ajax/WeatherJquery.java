package unina.webrtc.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WeatherJquery extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WeatherJquery() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("No GET method!");
        response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST!");
        String city = request.getParameter("cityName");
        if (city == null || city.equals("")) {
            System.out.println("cityName parameter is missing ...");
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        System.out.println("Client has sent: " + city);
        String report = getReport(city);
        if (report == null) {
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().append("<report>");
        response.getWriter().append(report);
        response.getWriter().append("</report>");
    }

    private String getReport(String city) {
        System.out.println("City: " + city);
        StringBuffer report = new StringBuffer();
        if (city.toLowerCase().equals("napoli")) {
            report.append("In Naples sun is shining as usual!");
            return report.toString();
        } else if (city.toLowerCase().equals("milano")) {
            report.append("In Milan it's foggy as usual...");
            return report.toString();
        } else {
            report.append("City " + city + " is not present in our database, sorry");
            return report.toString();
        }
    }
}