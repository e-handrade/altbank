package br.com.altbank.api;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @ConfigProperty(name = "altbank.api.key")
    String apiKey;

    @Test
    void testHelloEndpoint() {
        given()
                .header("X-API-KEY", apiKey)
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from Quarkus REST"));
    }

}