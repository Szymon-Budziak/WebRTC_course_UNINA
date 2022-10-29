package unina.webrtc.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AutoComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String[] suggestions = {
            "Topolino",
            "Pippo",
            "Minnie",
            "Pluto",
            "Gambadilegno",
            "Orazio",
            "Clarabella",
            "Qui",
            "Quo",
            "Qua",
            "Paperino",
            "Paperina",
            "Gastone",
            "Paperoga",
            "Zio Paperone",
            "Nonna Papera",
            "Ciccio",
            "Archimede",
            "Pico de Paperis"
    };

    public AutoComplete() {
        super();
        System.out.println("Suggestions:");
        for (int i = 0; i < suggestions.length; i++) {
            System.out.println(i + ": " + suggestions[i]);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("No GET, POST only ...");
        response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetId = request.getParameter("id");
        if (targetId == null || targetId.equals("")) {
            System.out.println("id parameter is missing ...");
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        System.out.println("Client has sent: " + targetId);
        targetId = targetId.trim().toLowerCase();
        boolean addedSomething = false;
        StringBuffer xmlReply = new StringBuffer();

        xmlReply.append("<response>");
        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().startsWith(targetId)) {
                addedSomething = true;
                xmlReply.append("<suggestion>" + suggestion + "</suggestion>");
            }
        }
        xmlReply.append("</response>");
        if (addedSomething) {
            System.out.println(xmlReply.toString());
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(xmlReply.toString());
        } else {
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
