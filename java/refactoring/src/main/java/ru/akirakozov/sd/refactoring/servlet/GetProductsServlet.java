package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static ru.akirakozov.sd.refactoring.database.ProductDataBaseInteraction.SELECT_ALL_FROM_PRODUCT_TABLE;
import static ru.akirakozov.sd.refactoring.database.ProductDataBaseInteraction.getResponseFromDataBase;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {
    private String DB_URL;

    public GetProductsServlet(String dbUrl) {
        DB_URL = dbUrl;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String res = getResponseFromDataBase(DB_URL, SELECT_ALL_FROM_PRODUCT_TABLE);

        response.getWriter().println("<html><body>");
        response.getWriter().print(res);
        response.getWriter().println("</body></html>");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
