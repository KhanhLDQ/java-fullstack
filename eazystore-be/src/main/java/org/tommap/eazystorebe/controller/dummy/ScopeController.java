package org.tommap.eazystorebe.controller.dummy;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.eazystorebe.scope.ApplicationScopeBean;
import org.tommap.eazystorebe.scope.RequestScopeBean;
import org.tommap.eazystorebe.scope.SessionScopeBean;

@RestController
@RequestMapping("/api/v1/scopes")
@Hidden
@RequiredArgsConstructor
public class ScopeController {
    private final RequestScopeBean requestScopeBean;
    private final SessionScopeBean sessionScopeBean;
    private final ApplicationScopeBean applicationScopeBean;

    @GetMapping("/request-scope")
    public ResponseEntity<String> testRequestScope() {
        requestScopeBean.setUserName("KhanhLe-Request-Scope");

        return ResponseEntity.ok().body(requestScopeBean.getUserName());
    }

    @GetMapping("/session-scope")
    public ResponseEntity<String> testSessionScope() {
        sessionScopeBean.setUserName("Tom-Session-Scope");

        return ResponseEntity.ok().body(sessionScopeBean.getUserName());
    }

    @GetMapping("/application-scope")
    public ResponseEntity<Integer> testApplicationScope() {
        applicationScopeBean.incrementVisitorCount();

        return ResponseEntity.ok().body(applicationScopeBean.getVisitorCount());
    }

    @GetMapping("/test")
//    public ResponseEntity<String> testScope() {
//        return ResponseEntity.ok().body(requestScopeBean.getUserName());
//        return ResponseEntity.ok().body(sessionScopeBean.getUserName());
    public ResponseEntity<Integer> testScope() {
        return ResponseEntity.ok().body(applicationScopeBean.getVisitorCount());
    }
}
