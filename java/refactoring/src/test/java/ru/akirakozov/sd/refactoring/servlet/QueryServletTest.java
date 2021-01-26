package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static ru.akirakozov.sd.refactoring.servlet.utils.ProductDataBaseUtils.*;
import static ru.akirakozov.sd.refactoring.servlet.utils.ProductHttpUtils.*;

public class QueryServletTest {
    private final String dbUrl = "jdbc:sqlite:test.db";

    @Before
    public void createNewTable() {
        executeOnDataBase(dbUrl, DROP_PRODUCT_TABLE);
        executeOnDataBase(dbUrl, CREATE_PRODUCT_TABLE);
    }

    @Test
    public void sumForZero() {
        String expectedResponse = "<html><body>\n" +
                "Summary price: \n" +
                "0\n" +
                "</body></html>\n";

        String res = getResponse(GET_SUM_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void sumForOne() {
        initWithOneProduct();

        String expectedResponse = "<html><body>\n" +
                "Summary price: \n" +
                "1000000\n" +
                "</body></html>\n";

        String res = getResponse(GET_SUM_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void sumForMany() {
        initWitThreeProducts();

        String expectedResponse = "<html><body>\n" +
                "Summary price: \n" +
                "4500000\n" +
                "</body></html>\n";

        String res = getResponse(GET_SUM_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void minForZero() {
        String expectedResponse = "<html><body>\n" +
                "<h1>Product with min price: </h1>\n" +
                "</body></html>\n";

        String res = getResponse(GET_MIN_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void minForOne() {
        initWithOneProduct();
        String expectedResponse = "<html><body>\n" +
                "<h1>Product with min price: </h1>\n" +
                "passport\t1000000</br>\n" +
                "</body></html>\n";

        String res = getResponse(GET_MIN_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void minForMany() {
        initWitThreeProducts();
        String expectedResponse = "<html><body>\n" +
                "<h1>Product with min price: </h1>\n" +
                "drivingLicense\t500000</br>\n" +
                "</body></html>\n";

        String res = getResponse(GET_MIN_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void maxForZero() {
        String expectedResponse = "<html><body>\n" +
                "<h1>Product with max price: </h1>\n" +
                "</body></html>\n";

        String res = getResponse(GET_MAX_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void maxForOne() {
        initWithOneProduct();
        String expectedResponse = "<html><body>\n" +
                "<h1>Product with max price: </h1>\n" +
                "passport\t1000000</br>\n" +
                "</body></html>\n";

        String res = getResponse(GET_MAX_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void maxForMany() {
        initWitThreeProducts();
        String expectedResponse = "<html><body>\n" +
                "<h1>Product with max price: </h1>\n" +
                "creditCard\t3000000</br>\n" +
                "</body></html>\n";

        String res = getResponse(GET_MAX_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void countForZero() {
        String expectedResponse = "<html><body>\n" +
                "Number of products: \n" +
                "0\n" +
                "</body></html>\n";

        String res = getResponse(GET_COUNT_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void countForOne() {
        initWithOneProduct();
        String expectedResponse = "<html><body>\n" +
                "Number of products: \n" +
                "1\n" +
                "</body></html>\n";

        String res = getResponse(GET_COUNT_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void countForMany() {
        initWitThreeProducts();
        String expectedResponse = "<html><body>\n" +
                "Number of products: \n" +
                "3\n" +
                "</body></html>\n";

        String res = getResponse(GET_COUNT_REQUEST);
        Assert.assertEquals(expectedResponse, res);
    }

    @Test
    public void uncknownCommand() {
        initWitThreeProducts();
        String expectedResponse = "Unknown command: get-things-done\n";

        String res = getResponse("http://localhost:8081/query?command=get-things-done");
        Assert.assertEquals(expectedResponse, res);
    }

    private void initWithOneProduct() {
        addProduct("passport", 1000000);
    }

    private void initWitThreeProducts() {
        addProduct("passport", 1000000);
        addProduct("creditCard", 3000000);
        addProduct("drivingLicense", 500000);
    }

}