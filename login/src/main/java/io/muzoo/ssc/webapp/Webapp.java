package io.muzoo.ssc.webapp;

import io.muzoo.ssc.assignment.tracker.SscAssignment;
import io.muzoo.ssc.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class Webapp extends SscAssignment {

    public static void main(String[] args) throws Exception {
        TomcatEnvironment.init();
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(TomcatEnvironment.getWorkDir().getAbsolutePath());
        tomcat.setPort(8082);
        tomcat.getConnector();

        SecurityService securityService = new SecurityService();
        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);

        Context ctx = tomcat.addWebapp("", TomcatEnvironment.getDocBase().getAbsolutePath());

        servletRouter.init(ctx);

        tomcat.start();
        tomcat.getServer().await();
    }
}
