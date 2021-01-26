package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.utils.RequestParametersParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static ru.akirakozov.sd.refactoring.database.ProductDataBaseInteraction.*;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {
    private String DB_URL;

    public AddProductServlet(String dbUrl) {
        DB_URL = dbUrl;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = RequestParametersParser.parseName(request);
        long price = RequestParametersParser.parsePrice(request);

        executeOnDataBase(DB_URL, String.format(
                ADD_TO_PRODUCT_TABLE,
                name,
                price
        ));

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("OK");
    }
}
