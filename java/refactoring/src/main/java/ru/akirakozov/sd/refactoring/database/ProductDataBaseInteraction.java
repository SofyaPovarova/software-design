package ru.akirakozov.sd.refactoring.database;

import java.sql.*;

public class ProductDataBaseInteraction {
    public static String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS PRODUCT" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " PRICE          INT     NOT NULL)";
    public static String DROP_PRODUCT_TABLE = "DROP TABLE PRODUCT";
    public static String SELECT_ALL_FROM_PRODUCT_TABLE = "SELECT * FROM PRODUCT";
    public static final String ADD_TO_PRODUCT_TABLE = "INSERT INTO PRODUCT " +
            "(NAME, PRICE) VALUES (\"%s\", \"%d\")";
    public static String MAX_FROM_PRODUCT_TABLE = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
    public static String MIN_FROM_PRODUCT_TABLE = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
    public static String SUM_PRICES_FROM_PRODUCT_TABLE = "SELECT SUM(price) FROM PRODUCT";
    public static String COUNT_PRODUCT_TABLE = "SELECT COUNT(*) FROM PRODUCT";

    public static void executeOnDataBase(String dbUrl, String sql) {
        try (Connection c = DriverManager.getConnection(dbUrl)) {
            Statement stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String getResponseFromDataBase(String dbUrl, String sql) {
        ResultSet rs;
        String rsString = "";
        try (Connection c = DriverManager.getConnection(dbUrl)) {
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            rsString = buildStringFromResultSet(rs);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsString;
    }

    public static String buildStringFromResultSet(ResultSet rs) {
        StringBuilder sb = new StringBuilder();
        try {
            while (rs.next()) {
                sb.append(rs.getString("NAME\t"))
                        .append(rs.getInt("PRICE\n"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sb.toString();
    }
}
