package org.tommap.eazystorebe.controller.dummy;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.eazystorebe.model.request.dummy.CreateUserRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/dummy")
@Hidden
@Validated //on top of controller class
public class DummyController {
    @PostMapping("/create-user")
    //@RequestBody -> auto convert JSON data to Java object using Jackson (default lib for JSON processing)
    public String createUser(@RequestBody CreateUserRequest request) {
        System.out.println(request);
        return "User created successfully!!";
    }

    @PostMapping("/request-entity")
    public String requestEntity(RequestEntity<CreateUserRequest> request) { //wrap all data (include headers | body | params ...) inside the request to the RequestEntity object
        //not recommend using RequestEntity to read query param & path variable
        return String.format("request-body: %s, request-header: %s", request.getBody().toString(), request.getHeaders());
    }

    @GetMapping("/search")
    public String searchUser(
        /*
            - @RequestParam -> accept query parameters
                + default
                    - method argument name is used as the parameter name -> can customize
                    - required -> if you do not pass the parameter - server throws 400 Bad Request error
                        + to make it optional -> set required: false
                + can provide a default value to avoid null when the parameter is missing
         */
        @Size(min = 5, max = 30) @RequestParam(required = false, defaultValue = "guest", name = "name") String userName
    ) {
        return String.format("Search for user: %s", userName);
    }

    @GetMapping("/multiple-search")
    public String multipleSearchUser(
        @RequestParam String firstName,
        @RequestParam String lastName
    ) {
        return String.format("Search for user: %s %s", firstName, lastName);
    }

    @GetMapping("/search-with-map")
    public String searchWithMap(@RequestParam Map<String, String> params) {
        return String.format("Search for user: %s %s", params.get("firstName"), params.get("lastName"));
    }

    @GetMapping("/users/{id}")
    public String getUserDetails(@PathVariable Long id) {
        return String.format("User id: %d", id);
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public String getUserPostDetails(
        @PathVariable Long userId, //cannot set default value
        @PathVariable Long postId
    ) {
        return String.format("User id: %d, post id: %d", userId, postId);
    }

    @GetMapping("/headers")
    public String readHeaders(
        @RequestHeader("User-Agent") String userAgent, //extract custom headers from the request -> throw 400 Bad Request in case of missing
        @RequestHeader("User-Location") String location
    ) {
        return String.format("User-Agent: %s, User-Location: %s", userAgent, location);
    }

    @GetMapping("/map-headers")
    public String readMapHeaders(@RequestHeader Map<String, String> headers) {
        return String.format("User-Agent: %s", headers.get("user-agent"));
    }

    @GetMapping("/http-headers")
    public String readHttpHeaders(@RequestHeader HttpHeaders headers) {
        return String.format("User-Location: %s", headers.get("User-Location"));
    }
}
