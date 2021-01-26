package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static ru.akirakozov.sd.refactoring.servlet.ProductDataBaseUtils.*;
import static ru.akirakozov.sd.refactoring.servlet.ProductHttpUtils.addProduct;
import static ru.akirakozov.sd.refactoring.servlet.ProductHttpUtils.getProducts;

public class GetProductsServletTest {
    private final String dbUrl = "jdbc:sqlite:test.db";

    @Before
    public void createNewTable() {
        executeOnDataBase(dbUrl, DROP_PRODUCT_TABLE);
        executeOnDataBase(dbUrl, CREATE_PRODUCT_TABLE);
    }

    @Test
    public void getZeroProducts() {
        String allProducts = "<html><body>\n" +
                "</body></html>\n";

        String res = getProducts();
        Assert.assertEquals(allProducts, res);
    }

    @Test
    public void addAndGetOneProducts() {
        addProduct("passport", 1000000);
        String allProducts = "<html><body>\n" +
                "passport\t1000000</br>\n" +
                "</body></html>\n";

        String res = getProducts();
        Assert.assertEquals(allProducts, res);
    }

    @Test
    public void addAndGetAllProducts() {
        addProduct("passport", 1000000);
        addProduct("creditCard", 3000000);
        addProduct("drivingLicense", 500000);

        String allProducts = "<html><body>\n" +
                "passport\t1000000</br>\n" +
                "creditCard\t3000000</br>\n" +
                "drivingLicense\t500000</br>\n" +
                "</body></html>\n";

        String res = getProducts();
        Assert.assertEquals(allProducts, res);
    }
}