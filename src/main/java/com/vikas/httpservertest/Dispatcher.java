package com.vikas.httpservertest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcherServlet", value = "/server")
public class Dispatcher extends HttpServlet {

    private static final String SUM_URI = "/sum";
    private static final String SUBTRACT_URI = "/subtract";
    private static final String MULTIPLY_URI = "/multiply";
    private static final String DIVIDE_URI = "/divide";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get Request");


        String uri = req.getRequestURI().substring(req.getContextPath().length());
        System.out.println("uri: " + uri);

        switch (uri) {
            case SUM_URI:
                Calculator cal = getCalculator(req);
                int sum = cal.sum();
                createResponse("sum: " + sum, resp);
                break;

            case SUBTRACT_URI:
                cal = getCalculator(req);
                int subtract = cal.subtract();
                createResponse("subtract: " + subtract, resp);
                break;

            case MULTIPLY_URI:
                cal = getCalculator(req);
                int multiply = cal.multiply();
                createResponse("multiply: " + multiply, resp);
                break;

            case DIVIDE_URI:
                cal = getCalculator(req);
                int divide = cal.divide();
                createResponse("divide: " + divide, resp);
                break;

            default:
                createResponse("BAD Request. uri: " + uri, resp);
        }
    }

    private void createResponse(String content, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write(content.getBytes());
        out.flush();
        out.close();
    }

    private Calculator getCalculator(HttpServletRequest req) {
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
        return new Calculator(num1, num2);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post Request");
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello heroku".getBytes());
        out.flush();
        out.close();
    }
}
