package org.example.Lab3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Lab_3_my_version {
    private Faker faker;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        faker = new Faker();
    }

    @Test
    public void testGetPetById() {
        int petId = 1;
        given()
                .when()
                .get("/pet/{petId}", petId)
                .then()
                .statusCode(200)
                .body("id", equalTo(petId));
    }

    @Test
    public void testUpdatePet() {
        int petId = 1; // Replace with desired pet ID
        String updatedName = faker.name().firstName();
        String updatedStatus = "sold";

        given()
                .contentType("application/json")
                .body("{ \"id\": " + petId + ", \"name\": \"" + updatedName + "\", \"status\": \"" + updatedStatus + "\" }")
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedName))
                .body("status", equalTo(updatedStatus));
    }

    @Test
    public void testAddNewPet() {
        String petName = faker.name().firstName();
        String petStatus = "available";

        given()
                .contentType("application/json")
                .body("{ \"name\": \"" + petName + "\", \"status\": \"" + petStatus + "\" }")
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo(petName))
                .body("status", equalTo(petStatus));
    }
}
