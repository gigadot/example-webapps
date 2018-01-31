/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp;

import io.muic.ooc.webapp.servlet.HomeServlet;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.servlet.LoginServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gigadot
 */
public class ServletRouter {

    private static final List<Class<? extends Routeable>> routeables = new ArrayList<>();
    static {
        routeables.add(HomeServlet.class);
        routeables.add(LoginServlet.class);
    }

    private SecurityService securityService;


    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void init(Context ctx) {
        for (Class<? extends Routeable> routeableClass: routeables) {
            try {
                Routeable routeable = routeableClass.newInstance();
                routeable.setSecurityService(securityService);
                String name = routeable.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, (HttpServlet) routeable);
                ctx.addServletMapping(routeable.getMapping(), name);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
