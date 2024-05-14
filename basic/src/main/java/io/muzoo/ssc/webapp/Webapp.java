package io.muzoo.ssc.webapp;

import jakarta.servlet.Servlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class Webapp {

    public static void main(String[] args) throws Exception {
        TomcatEnvironment.init();
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(TomcatEnvironment.getWorkDir().getAbsolutePath());
        tomcat.setPort(8082);
        tomcat.getConnector();
        Context ctx = tomcat.addWebapp("", TomcatEnvironment.getDocBase().getAbsolutePath());

        Servlet adminServlet = new AdminServlet();
        Tomcat.addServlet(ctx, "AdminServlet", adminServlet);
        ctx.addServletMappingDecoded("/admin", "AdminServlet");

        Servlet homeServlet = new HomeServlet();
        Tomcat.addServlet(ctx, "HomeServlet", homeServlet);
        // TRICK: mapping with index.jsp, allow access to root path "/"
        ctx.addServletMappingDecoded("/index.jsp", "HomeServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
