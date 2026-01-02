package org.tommap.eazystorebe.scope;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/*
    - SINGLETON
        + default scope inside spring framework
        + same object instance will be returned every time we refer a bean inside the code
        + instantiation
            - eager
                + default behavior
                + be created during the startup of the application
                + if bean is not able to create due to any exceptions -> server cannot start
            - lazy
                + need to explicitly configure using @Lazy
                + only be created when the app is trying to refer the bean for the first time
    - PROTOTYPE
        + new object instance will be returned every time we refer a bean inside the code
        + need to explicitly configure using @Scope()
 */

@Getter @Setter
@Component
/*
    REQUEST_SCOPE
    - new bean instance is created for each http request -> only be valid for the duration of a request
    - how it works?
        + each http request gets a new instance of RequestScopeBean
        + if users send multiple requests -> each gets a separate instance
        + as soon as the request is complete -> bean is destroyed
    - e.g. to store temporary user inputs
 */
@RequestScope
@Slf4j
public class RequestScopeBean {
    private String userName;

    public RequestScopeBean() {
        log.info("RequestScopeBean initialized!!");
    }
}
