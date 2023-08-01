package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //Extract the title
    @Test
    public void testTitle() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the total number of record
    @Test
    public void testTotalNumber() {
        List<String> title = response.extract().path("title");
        int noOfRecords = title.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + noOfRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the body of 15th record
    @Test
    public void testBodyRecord() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the user_id of all the records
    @Test
    public void testUserId() {
        List<String> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records are : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the title of all the records
    @Test
    public void testtitleOfAlltheRecords() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The itle of all the records are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the title of all records whose user_id = 4040731
    @Test
    public void testtitleOfAlltheRecordsId() {
        List<String> title = response.extract().path("findAll{it.user_id = 4040731}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 4040731 are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the body of all records whose id = 56989
    @Test
    public void testBodyOfAllRecords() {
        List<String> body = response.extract().path("findAll{it.id = '56989'}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of body of all records whose id = 4040712 are : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
