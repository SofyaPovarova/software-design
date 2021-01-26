package ru.akirakozov.sd.refactoring.writer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseWriter {

    public void writeHtmlResponse(HttpServletResponse response, String header, String res) throws IOException {
        response.getWriter().println("<html><body>");
        response.getWriter().println(header);
        response.getWriter().print(res);
        response.getWriter().println("</body></html>");
    }

    public void writeUnknownResponse(HttpServletResponse response, String command) throws IOException {
        response.getWriter().println("Unknown command: " + command);
    }

    public void writeOkResponse(HttpServletResponse response) throws IOException {
        response.getWriter().println("OK");
    }

    public void endUpHtmlResponse(HttpServletResponse response) {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
