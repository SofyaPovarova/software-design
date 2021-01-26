package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.utils.RequestParametersParser;
import ru.akirakozov.sd.refactoring.writer.ResponseWriter;

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
    private final String DB_URL;
    private final ResponseWriter responseWriter;

    public AddProductServlet(String dbUrl) {
        DB_URL = dbUrl;
        responseWriter = new ResponseWriter();
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
        responseWriter.endUpHtmlResponse(response);
        responseWriter.writeOkResponse(response);
    }
}
