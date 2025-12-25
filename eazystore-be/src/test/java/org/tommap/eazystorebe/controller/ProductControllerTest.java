package org.tommap.eazystorebe.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.tommap.eazystorebe.AbstractIntegrationTest;
import org.tommap.eazystorebe.model.dto.ProductDto;
import org.tommap.eazystorebe.model.entity.Product;
import org.tommap.eazystorebe.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class ProductControllerTest extends AbstractIntegrationTest {
    @Value("${app.base-test-url}")
    String baseTestUrl;

    @LocalServerPort
    int port;

    @Autowired
    ProductRepository productRepository;

    @BeforeAll
    void setUpAll() {
        RestAssured.baseURI = baseTestUrl;
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1";
    }

    @BeforeEach
    void initData() {
        productRepository.deleteAll();

        var firstProduct = new Product();
        firstProduct.setName("Developer");
        firstProduct.setDescription("Code Wizard!");
        firstProduct.setPrice(BigDecimal.valueOf(5.00));
        firstProduct.setPopularity(85);
        firstProduct.setImageUrl("/stickers/developer.png");

        var secondProduct = new Product();
        secondProduct.setName("Break");
        secondProduct.setDescription("Hey, lets take a breather and start fresh on the next line");
        secondProduct.setPrice(BigDecimal.valueOf(4.50));
        secondProduct.setPopularity(40);
        secondProduct.setImageUrl("/stickers/break.png");

        productRepository.saveAll(List.of(firstProduct, secondProduct));
    }

    @Test
    @Order(1)
    void testContainerIsRunning() {
        assertTrue(mysql.isCreated(), "mySQL is not created!");
        assertTrue(mysql.isRunning(), "mySQL is not running!");
    }

    @Test
    @Order(2)
    @DisplayName("get-products")
    void testGetProducts_ShouldReturnData() {
        //arrange

        //act & assert
        List<ProductDto> response =
            given()
                .log()
                    .all()
                .accept(JSON)
            .when()
                .get("/products")
            .then()
                .log()
                    .all()
                .statusCode(200)
                .extract()
                    .body()
                        .jsonPath()
                            .getList(".", ProductDto.class);

        assertThat(response)
            .hasSize(2)
            .extracting(ProductDto::getName)
            .containsExactlyInAnyOrder("Developer", "Break");
    }
}
