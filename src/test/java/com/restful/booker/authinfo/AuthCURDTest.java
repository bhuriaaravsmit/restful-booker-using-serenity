package com.restful.booker.authinfo;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class AuthCURDTest extends TestBase {

    static String username ="admin";
static  String password ="password123";

    @Title("This will create a new token")
    @Test
    public void createToken() {
        AuthPojo authpojo = new AuthPojo();
        authpojo.setUsername(username);
        authpojo.setPassword(password);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                //.header("Accept", "application/json")
                .when()
                .body(authpojo)
                .post()
                .then().log().body().statusCode(200);
    }







}
