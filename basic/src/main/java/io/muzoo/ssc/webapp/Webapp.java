package io.muzoo.ssc.webapp;

import java.io.File;
import javax.servlet.ServletException;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Webapp {

    public static void main(String[] args) {

        File docBase = new File("src/main/webapp/");
        docBase.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        Context ctx;
        try {
            ctx = tomcat.addWebapp("", docBase.getAbsolutePath());
            AdminServlet adminServlet = new AdminServlet();
            Tomcat.addServlet(ctx, "AdminServlet", adminServlet);
            ctx.addServletMapping("/admin", "AdminServlet");

            HomeServlet homeServlet = new HomeServlet();
            Tomcat.addServlet(ctx, "HomeServlet", homeServlet);
            // TRICK: mapping with index.jsp, allow access to root path "/"
            ctx.addServletMapping("/index.jsp", "HomeServlet");

            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }

    }
}
