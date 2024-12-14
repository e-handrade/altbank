package br.com.altbank.api;

import br.com.altbank.config.ConfigLoader;
import br.com.altbank.security.RequiresApiKey;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

//    @ConfigProperty(name = "altbank.api.key")
//    String apiKey;
    String apiKey = ConfigLoader.getEnv("altbank.api.key");


    @Test
    @RequiresApiKey
    void testHelloEndpoint() {
        given()
                .header("X-API-KEY", apiKey)
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from Quarkus REST"));
    }

}