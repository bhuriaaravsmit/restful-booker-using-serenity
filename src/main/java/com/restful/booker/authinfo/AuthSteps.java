package com.restful.booker.authinfo;

import com.restful.booker.model.AuthPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class AuthSteps {

    @Step("Creating new auth token")
    public ValidatableResponse createToken(String username , String password){

        AuthPojo authpojo =new AuthPojo();
        authpojo.setUsername(username);
        authpojo.setPassword(password);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(authpojo)
                .post()
                .then();
    }

}
