package org.tommap.eazystorebe.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.tommap.eazystorebe.AbstractIntegrationTest;
import org.tommap.eazystorebe.model.request.ContactRequest;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class ContactControllerTest extends AbstractIntegrationTest {
    @Value("${app.base-test-url}")
    String baseTestUrl;

    @LocalServerPort
    int port;

    @BeforeAll
    void setUpAll() {
        RestAssured.baseURI = baseTestUrl;
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1";
    }

    @Test
    @Order(1)
    void testContainerIsRunning() {
        assertTrue(mysql.isCreated(), "mySQL is not created!");
        assertTrue(mysql.isRunning(), "mySQL is not running!");
    }

    @Test
    @Order(2)
    @DisplayName("save-contact")
    void testSaveContact_WhenValidRequestProvided_ShouldProceedSuccessfully() {
        //arrange
        var request = ContactRequest.builder()
                .name("Khanh Le")
                .email("khanh.le@example.com")
                .mobileNumber("1234567890")
                .message("This is a test message for the contact form!")
                .build();

        //act & assert
        given() //setup HTTP request details -> headers - params - body
            .log() //help to debug a failed test method
                .all()
            .contentType(JSON)
            .body(request)
        .when() //specify HTTP method & endpoint to call
            .post("/contacts")
        .then()
            .log()
                .all()
            .statusCode(201)
            .body(equalTo("Request processed successfully"));
    }
}
