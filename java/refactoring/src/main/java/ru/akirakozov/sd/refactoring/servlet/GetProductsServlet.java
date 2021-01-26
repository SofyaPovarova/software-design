package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.writer.ResponseWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.akirakozov.sd.refactoring.database.ProductDataBaseInteraction.*;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {
    private final String DB_URL;
    private final ResponseWriter responseWriter;

    public GetProductsServlet(String dbUrl) {
        DB_URL = dbUrl;
        responseWriter = new ResponseWriter();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String header = "";
        String res = getResponseFromDataBase(DB_URL, SELECT_ALL_FROM_PRODUCT_TABLE);
        responseWriter.writeHtmlResponse(response, header, res);
        responseWriter.endUpHtmlResponse(response);
    }
}
