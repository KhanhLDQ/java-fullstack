package org.tommap.eazystorebe.scope;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Getter @Setter
/*
    SESSION_SCOPE
    - new bean instance is created for each user session
    - how it works?
        + when users log in -> a SessionScopeBean instance is created
        + the same bean instance is used for multiple requests from the same user
        + when the session expires (log out | close browser | clear browser cache ...) -> the bean is destroyed
    - e.g. to store user-specific data across multiple requests in a single session -> shopping cart info
 */
@SessionScope
@Slf4j
public class SessionScopeBean {
    private String userName;

    public SessionScopeBean() {
        log.info("SessionScopeBean initialized!!");
    }
}
