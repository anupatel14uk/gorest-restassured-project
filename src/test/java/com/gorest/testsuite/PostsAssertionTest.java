package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
    }


    //Verify the if the total record is 25
    @Test
    public void testVerifyTheOfTheTotal() {
        response.body("size", equalTo(25));
    }

    //Verify the if the title of id = 56978 is equal to ”Benevolentia anser cum corroboro pariatur amor carpo angelus.”
    @Test
    public void testVerifyTheOfTheTitle() {
        response.body("find{it.id == 56978}.title", equalTo("Rerum omnis sursum damno terror."));
    }



    //Check the multiple ids in the ArrayList (56973, 56970,56985)
    @Test
    public void testCheckTheMultipleId() {
        response.body("id", hasItems(56985,56970, 56973));
    }

  //Verify the body of userid = 56980 is
    //        "user_id": 4040719,
    //        "Trepide alo animi. Et amita sopor. Casus est cunabula.
    //        Voluptatibus vinco quae. Vix desino utpote. Animi clamo umquam.
    //        Considero dolor absens. Tam angelus tabesco. Copiose hic cui.
    //        Sponte dignissimos turbo. Viriliter clarus centum. Vergo validus curatio.
    //        Coadunatio stips vita. Doloremque accendo uredo. Laborum sum paulatim.
    //        Cito desipio advenio.Aer qui vulpes. Allatus sol turba. Quisquam volaticus absorbeo."




    @Test
    public void testBodyOfUserId() {
        response.body("find{it.id == 56980}.body", equalTo("Trepide alo animi. Et amita sopor. Casus est cunabula. Voluptatibus vinco quae. Vix desino utpote. Animi clamo umquam. Considero dolor absens. Tam angelus tabesco. Copiose hic cui. Sponte dignissimos turbo. Viriliter clarus centum. Vergo validus curatio. Coadunatio stips vita. Doloremque accendo uredo. Laborum sum paulatim. Cito desipio advenio. Aer qui vulpes. Allatus sol turba. Quisquam volaticus absorbeo."));
    }
}
