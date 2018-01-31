package io.muic.ooc.webapp;

import java.io.File;
import javax.servlet.ServletException;

import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.servlet.HomeServlet;
import io.muic.ooc.webapp.servlet.LoginServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Webapp {

    public static void main(String[] args) {

        File docBase = new File("src/main/webapp/");
        docBase.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        SecurityService securityService = new SecurityService();

        Context ctx;
        try {
            ctx = tomcat.addWebapp("", docBase.getAbsolutePath());
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.setSecurityService(securityService);
            Tomcat.addServlet(ctx, "LoginServlet", loginServlet);
            ctx.addServletMapping("/login", "LoginServlet");

            HomeServlet homeServlet = new HomeServlet();
            homeServlet.setSecurityManager(securityService);
            Tomcat.addServlet(ctx, "HomeServlet", homeServlet);
            ctx.addServletMapping("/index.jsp", "HomeServlet");

            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }

    }
}
