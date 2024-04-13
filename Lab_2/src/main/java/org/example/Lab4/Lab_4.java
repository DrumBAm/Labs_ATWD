package org.example.Lab4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Lab_4 {

    @BeforeClass
    public void setUp() {
        // Встановлення базового URL мок-сервера
        RestAssured.baseURI = "https://b74275d6-af11-4f82-80fd-fad5d277fd1f.mock.pstmn.io";
    }

    @Test
    public void testGetRequest() {
        // Перевірка GET-запиту
        given()
                .when()
                .get("/ownerName/success")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testPostRequest() {
        // Перевірка POST-запиту
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/createSomething?permission=yes")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPutRequest() {
        // Перевірка PUT-запиту
        given()
                .contentType(ContentType.JSON)
                .when()
                .put("/updateMe")
                .then()
                .statusCode(500);
    }

    @Test
    public void testDeleteRequest() {
        // Перевірка DELETE-запиту
        when()
                .delete("/deleteWorld?SessionID=123456789")
                .then()
                .statusCode(410);
    }
}

