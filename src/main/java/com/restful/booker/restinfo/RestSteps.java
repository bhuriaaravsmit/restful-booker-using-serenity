package com.restful.booker.restinfo;

import com.restful.booker.constants.EndPoint;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.RestPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class RestSteps {


    @Step("Creating booking with firstName : {0}, lastName: {1}, totalPrice: {2}, depositPaid: {3}, additionalNeeds: {4}")
    public ValidatableResponse creatingBooking(String firstName, String lastName, String totalPrice, String depositpaid, String additionalNeeds,String token) {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname(firstName);
        restPojo.setLastname(lastName);
        restPojo.setTotalprice(totalPrice);
        restPojo.setDepositpaid(depositpaid);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("12-12-2002");
        bookingdates.setCheckout("12-12-2003");
        restPojo.setBookingdates(bookingdates);
        restPojo.setAdditionalneeds(additionalNeeds);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .body(restPojo)
                .basePath("/booking")
                .post()
                .then().log().all().statusCode(200);

    }


    @Step("Updating booking with id : {0}, lastName: {1}, totalPrice: {2}, depositPaid: {3}, additionalNeeds: {4}, token: {5}, bookingId:{6}")
    public String updatingBooking(String firstName, String lastName, String totalPrice, String depositpaid, String additionalNeeds, String token, int bookingId){
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname(firstName);
        restPojo.setLastname(lastName);
        restPojo.setTotalprice(totalPrice);
        restPojo.setTotalprice(depositpaid);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("");
        bookingdates.setCheckout("");
        restPojo.setBookingdates(bookingdates);
        restPojo.setAdditionalneeds(additionalNeeds);

        return SerenityRest.given()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("bookingID", bookingId)
                .body(restPojo)
                .when()
                .put("/booking" + EndPoint.UPDATE_BOOKING_BY_ID)
                .then().log().all().statusCode(200)
                .extract()
                .path(" ");

    }


    @Step("Deleting booking  information with booking: {0}, token: {1}")
    public ValidatableResponse deleteBooking(int bookingId, String token) {
        return SerenityRest.given()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("bookingID", bookingId)
                .when()
                .delete("/booking" + EndPoint.DELETE_BOOKING_BY_ID)
                .then();
    }

    @Step("Fetch booking  information with booking: {0}, token: {1}")
    public ValidatableResponse fetchBooking(int bookingId, String token) {
        return SerenityRest.given()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("bookingID", bookingId)
                .when()
                .get("/booking" + EndPoint.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }



}
