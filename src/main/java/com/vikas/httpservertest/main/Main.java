package com.vikas.httpservertest.main;

import com.vikas.httpservertest.Dispatcher;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // configure the server
        // configure web applications
        tomcat.setBaseDir("temp");
        String contextPath = "/server";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);

        // Dispatcher Servlet (Front Controller)
        String servletName = "dispatcherServlet";
        String urlPattern = "/";
        tomcat.addServlet(contextPath, servletName, new Dispatcher());
        context.addServletMappingDecoded(urlPattern, servletName);

        // start tomcat server
        tomcat.start();
        tomcat.getServer().await();
    }

}
