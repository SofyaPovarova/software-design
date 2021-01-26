package ru.akirakozov.sd.refactoring.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestParametersParser {
    public static String parseName(HttpServletRequest request) {
        return request.getParameter("name");
    }

    public static Long parsePrice(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("price"));
    }
}
