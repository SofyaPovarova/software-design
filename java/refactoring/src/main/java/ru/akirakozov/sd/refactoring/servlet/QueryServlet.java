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
public class QueryServlet extends HttpServlet {
    private final String DB_URL;
    private final ResponseWriter responseWriter;

    public QueryServlet(String dbUrl) {
        DB_URL = dbUrl;
        responseWriter = new ResponseWriter();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        String header;
        String res;
        switch (command) {
            case ("max"):
                header = "<h1>Product with max price: </h1>";
                res = getResponseFromDataBase(DB_URL, MAX_FROM_PRODUCT_TABLE);
                responseWriter.writeHtmlResponse(response, header, res);
                break;
            case ("min"):
                header = "<h1>Product with min price: </h1>";
                res = getResponseFromDataBase(DB_URL, MIN_FROM_PRODUCT_TABLE);
                responseWriter.writeHtmlResponse(response, header, res);
                break;
            case ("sum"):
                header = "Summary price: ";
                res = getResponseFromDataBase(DB_URL, SUM_PRICES_FROM_PRODUCT_TABLE);
                responseWriter.writeHtmlResponse(response, header, res);
                break;
            case ("count"):
                header = "Number of products: ";
                res = getResponseFromDataBase(DB_URL, COUNT_PRODUCT_TABLE);
                responseWriter.writeHtmlResponse(response, header, res);
                break;
            default:
                responseWriter.writeUnknownResponse(response, command);
        }

        responseWriter.endUpHtmlResponse(response);
    }

}
