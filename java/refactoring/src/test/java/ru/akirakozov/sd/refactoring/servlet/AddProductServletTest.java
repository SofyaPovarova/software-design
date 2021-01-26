package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static ru.akirakozov.sd.refactoring.servlet.utils.ProductDataBaseUtils.*;
import static ru.akirakozov.sd.refactoring.servlet.utils.ProductHttpUtils.addProduct;

public class AddProductServletTest {
    private final String dbUrl = "jdbc:sqlite:test.db";
    private final String okResponse = "OK\n";

    @Before
    public void createNewTable() {
        executeOnDataBase(dbUrl, DROP_PRODUCT_TABLE);
        executeOnDataBase(dbUrl, CREATE_PRODUCT_TABLE);
    }

    @Test
    public void addOneProduct() {
        String result = addProduct("passport", 1000000);
        Assert.assertEquals(result, okResponse);

        String expectedRes = "passport1000000";
        String res = getResponseFromDataBase(dbUrl, SELECT_ALL_FROM_PRODUCT_TABLE);
        Assert.assertEquals(expectedRes, res);
    }

    @Test
    public void addThreeProducts() {
        String result = addProduct("passport", 1000000);
        Assert.assertEquals(result, okResponse);
        result = addProduct("creditCard", 3000000);
        Assert.assertEquals(result, okResponse);
        result = addProduct("drivingLicense", 500000);
        Assert.assertEquals(result, okResponse);

        String expectedRes = "passport1000000creditCard3000000drivingLicense500000";
        String res = getResponseFromDataBase(dbUrl, SELECT_ALL_FROM_PRODUCT_TABLE);
        Assert.assertEquals(expectedRes, res);
    }

}