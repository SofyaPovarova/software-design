package ru.akirakozov.sd.refactoring.servlet;

import java.sql.*;

public final class ProductDataBaseUtils {
    static String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS PRODUCT" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " PRICE          INT     NOT NULL)";

    static String DROP_PRODUCT_TABLE = "DROP TABLE PRODUCT";

    static String SELECT_ALL_FROM_PRODUCT_TABLE = "SELECT * FROM PRODUCT";

    static void executeOnDataBase(String dbUrl, String sql) {
        try (Connection c = DriverManager.getConnection(dbUrl)) {
            Statement stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static String getResponseFromDataBase(String dbUrl, String sql) {
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

    static String buildStringFromResultSet(ResultSet rs) {
        StringBuilder sb = new StringBuilder();
        try {
            while (rs.next()) {
                sb.append(rs.getString("NAME"));
                sb.append(rs.getInt("PRICE"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sb.toString();
    }

}
