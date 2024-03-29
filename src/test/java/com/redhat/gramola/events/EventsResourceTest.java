package com.redhat.gramola.events;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class EventsResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/events/welcome")
          .then()
             .statusCode(200)
             .body(is("Welcome"));
    }

}