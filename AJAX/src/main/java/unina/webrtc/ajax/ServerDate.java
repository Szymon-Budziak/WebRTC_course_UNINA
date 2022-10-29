package unina.webrtc.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServerDate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServerDate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET!");
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST!");
        GregorianCalendar gc = new GregorianCalendar();
        System.out.println("Day: " + gc.get(Calendar.DAY_OF_MONTH));
        System.out.println("Month: " + gc.get(Calendar.MONTH));
        System.out.println("Year: " + gc.get(Calendar.YEAR));
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().append("<response");
        response.getWriter().append("<day>" + gc.get(Calendar.DAY_OF_MONTH) + "</day>");
        response.getWriter().append("<month>" + gc.get(Calendar.MONTH) + "</month>");
        response.getWriter().append("<year>" + gc.get(Calendar.YEAR) + "</year>");
        response.getWriter().append("<hour>" + gc.get(Calendar.HOUR) + "</hour>");
        response.getWriter().append("<minute>" + gc.get(Calendar.MINUTE) + "</minute>");
        response.getWriter().append("<second>" + gc.get(Calendar.SECOND) + "</second>");
        response.getWriter().append("</response");
    }
}
