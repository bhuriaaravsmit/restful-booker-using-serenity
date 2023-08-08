package com.restful.booker.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase1 {
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RestAssured.basePath = "/booking";

    }

}
