package io.muzoo.ssc.webapp;

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
        tomcat.start();
        tomcat.getServer().await();
    }
}
