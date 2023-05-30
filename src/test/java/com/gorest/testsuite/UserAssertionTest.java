package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * Created By Ronak Patel
 */
public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    //  1. Verify the if the total record is 20
    @Test
    public void Test01() {
        response.body("size", equalTo(10));
    }

    //  2. Verify the if the name of id = 2322254 is equal to ”Anshula Panicker PhD”
    @Test
    public void Test02() {
        response.body("[0].name", equalTo("Chandraswaroopa Joshi"));
    }

    // 3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void Test03() {
        response.body("[6].name", equalTo("Prof. Veda Prajapat"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void Test04() {
        response.body("name", hasItems("Chandraswaroopa Joshi", "Anand Verma", "Amb. Ekaksh Bharadwaj"));
    }


    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void Test05() {
        response.body("[3].id", equalTo(2329070));
        response.body("[3].email", equalTo("amogh_patel@wolf.example"));

    }

    // 6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void Test06() {
        response.body("[7].status", equalTo("inactive"));
        response.body("[7].name", equalTo("Hiranmay Verma Sr."));
    }

    //  7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void Test07() {
        response.body("[1].gender", equalTo("male"));
        response.body("[1].name",equalTo( "Anand Verma"));

    }
}
