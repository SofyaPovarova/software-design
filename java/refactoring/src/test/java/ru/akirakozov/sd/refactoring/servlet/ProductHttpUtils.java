package ru.akirakozov.sd.refactoring.servlet;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProductHttpUtils {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    private static final String ADD_PRODUCT_REQUEST = "http://localhost:8081/add-product?name=%s&price=%d";

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

}
