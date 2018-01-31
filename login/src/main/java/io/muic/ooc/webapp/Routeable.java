package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.SecurityService;

public interface Routeable {
    String getMapping();
    void setSecurityService(SecurityService securityService);
}
