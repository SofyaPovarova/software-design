package ru.akirakozov.sd.refactoring.servlet.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProductHttpUtils {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static final String ADD_PRODUCT_REQUEST = "http://localhost:8081/add-product?name=%s&price=%d";
    public static final String GET_PRODUCTS_REQUEST = "http://localhost:8081/get-products";
    public static final String GET_SUM_REQUEST = "http://localhost:8081/query?command=sum";
    public static final String GET_MIN_REQUEST = "http://localhost:8081/query?command=min";
    public static final String GET_MAX_REQUEST = "http://localhost:8081/query?command=max";
    public static final String GET_COUNT_REQUEST = "http://localhost:8081/query?command=count";

    public static String addProduct(String name, int price) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        String.format(
                                ADD_PRODUCT_REQUEST,
                                name,
                                price
                        )
                ))
                .build();

        return getResponse(request);
    }

    private static String getResponse(HttpRequest request) {
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }

    public static String getResponse(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return getResponse(request);
    }

}
