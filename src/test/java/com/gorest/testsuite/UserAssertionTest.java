package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void VerifyTheIfTheTotalRecordIs20() {
        response.body("size", equalTo(20));
    }

    // 2. Verify the if the name of id = 4040732 is equal to ”Pres. Lakshmi Chaturvedi”
    @Test
    public void testVerifyName() {

        response.body("[3].name", equalTo("Pres. Lakshmi Chaturvedi"));
    }

    // 3. Check the single ‘Name’ in the Array list (Jagdeep Nambeesan)
    @Test
    public void testCheckSingleName() {

        response.body("[2].name", equalTo("Jagdeep Nambeesan"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Adikavi Sethi, Tanushri Nayar, Niranjan Gill)
    @Test
    public void testCheckMultipleName() {

        response.body("name", hasItems("Adikavi Sethi", "Tanushri Nayar", "Niranjan Gill"));
    }


    // 5. Verify the email of userid = 4040715 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void testVerifyTheEmail() {

        response.body("[18].id", equalTo(4040715));
        response.body("[18].email", equalTo("deeptimay_panicker@bailey.test"));

    }

    // 6. Verify the status is “Active” of user name is “Adikavi Sethi”
    @Test
    public void testVerifyTheStatusIsActive() {

        response.body("[5].status", equalTo("active"));
        response.body("[5].name", equalTo("Adikavi Sethi"));

    }


    // 7. Verify the Gender = male of user name is  "Pres. Lakshmi Chaturvedi"
    @Test
    public void testVerifyTheGenderMale() {
        response.body("[3].gender", equalTo("male"));
        response.body("[3].name",equalTo( "Pres. Lakshmi Chaturvedi"));
    }
}
