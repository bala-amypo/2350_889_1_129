package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

public class HealthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setStatus(200);
        resp.setContentType("text/plain");
        resp.getWriter().write("BUNDLE-OK");
        resp.getWriter().flush();
    }
}
