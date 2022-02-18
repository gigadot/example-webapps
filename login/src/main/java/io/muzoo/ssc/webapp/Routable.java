package io.muzoo.ssc.webapp;

import io.muzoo.ssc.webapp.service.SecurityService;

public interface Routable {

    String getMapping();

    void setSecurityService(SecurityService securityService);
}
