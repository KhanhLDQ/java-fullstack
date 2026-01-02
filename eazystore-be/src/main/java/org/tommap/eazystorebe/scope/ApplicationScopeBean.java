package org.tommap.eazystorebe.scope;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@Getter
@Slf4j
/*
    APPLICATION_SCOPE
    - a single bean instance is shared across the entire application
    - how it works?
        + only one instance of ApplicationScopeBean is created
        + shared across all users and requests
        + the bean is only destroyed when the application stops
    - e.g. to store global statistics -> visitor count
 */
@ApplicationScope
public class ApplicationScopeBean {
    private int visitorCount = 0;

    public ApplicationScopeBean() {
        log.info("ApplicationScopeBean initialized!!");
    }

    public void incrementVisitorCount() {
        visitorCount++;
    }
}
