package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {
    static String name = "AnuUser" + TestUtils.getRandomValue();
    static String email = "AnuUser" + TestUtils.getRandomValue() + "@gmail.com";
    static String updatedEmail = "Updated" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "female";
    static String status = "active";
    static int userId;
    static   String token = "f8f231911770bd29394ba6350b92e3e88b00d7f482a4b763d244d3d42f395d19";


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
    }

    @Test()
    public void verifyUserACreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        // make request to server to create new user
        Response response = given()
                .headers("Content-Type", "application/json","Authorization", "Bearer "+token)
                .when()
                .body(userPojo)
                .post("/public/v2/users");

        //To fetch response from server
        response.then().log().all().statusCode(201);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        userId = jsonPath.getInt("id");
        System.out.println("user id "+ userId);
    }
    @Test
    public void verifyUserBUpdateSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(updatedEmail);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json","Authorization", "Bearer "+token)
                .when()
                .body(userPojo)
                .patch("/public/v2/users/" + userId);
        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        userId = jsonPath.getInt("id");
        System.out.println("user id "+ userId);
    }

    @Test
    public void verifyUserDeleteSuccessfully() {

        given()
                .headers("Content-Type", "application/json","Authorization", "Bearer "+token)
                .pathParam("id", userId)

                .when()
                .delete("/public/v2/users/{id}")//replace to user id When the delete
                .then()
                .statusCode(204);
    }
}
