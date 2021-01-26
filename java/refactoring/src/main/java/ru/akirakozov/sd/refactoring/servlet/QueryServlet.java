package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static ru.akirakozov.sd.refactoring.database.ProductDataBaseInteraction.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    private final String DB_URL;

    public QueryServlet(String dbUrl) {
        DB_URL = dbUrl;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with max price: </h1>");
            String res = getResponseFromDataBase(DB_URL, MAX_FROM_PRODUCT_TABLE);
            response.getWriter().print(res);
            response.getWriter().println("</body></html>");
        } else if ("min".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with min price: </h1>");
            String res = getResponseFromDataBase(DB_URL, MIN_FROM_PRODUCT_TABLE);
            response.getWriter().print(res);
            response.getWriter().println("</body></html>");
        } else if ("sum".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("Summary price: ");
            String res = getResponseFromDataBase(DB_URL, SUM_PRICES_FROM_PRODUCT_TABLE);
            response.getWriter().print(res);
            response.getWriter().println("</body></html>");
        } else if ("count".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("Number of products: ");
            String res = getResponseFromDataBase(DB_URL, COUNT_PRODUCT_TABLE);
            response.getWriter().print(res);
            response.getWriter().println("</body></html>");
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
