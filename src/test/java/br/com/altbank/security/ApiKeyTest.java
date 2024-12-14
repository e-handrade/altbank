package br.com.altbank.security;

import br.com.altbank.config.ConfigLoader;
import io.restassured.RestAssured;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ApiKeyTest {

//    @ConfigProperty(name = "altbank.api.key")
//    String apiKey;
String apiKey = ConfigLoader.getEnv("altbank.api.key");


    @Test
    @RequiresApiKey
    public void testUnauthorizedAccess() {
        RestAssured.given()
                .when()
                .get("/hello")
                .then()
                .statusCode(401);
    }

    @Test
    @RequiresApiKey
    public void testAuthorizedAccess() {
        RestAssured.given()
                .header("X-API-KEY", apiKey)
                .when()
                .get("/hello")
                .then()
                .statusCode(200);
    }

}
