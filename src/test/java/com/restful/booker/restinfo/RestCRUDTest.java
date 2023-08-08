package com.restful.booker.restinfo;

import com.restful.booker.constants.EndPoint;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.RestPojo;
import com.restful.booker.testbase.TestBase1;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RestCRUDTest extends TestBase1 {

    static String token = "372a1c9a1abd1f2";

    int id = 14678;

    @Title("This will create new booking IDs")
    @Test
    public void getBookingID() {
        SerenityRest.given()
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .when()
                .get()
                .then().log().all().statusCode(200);
    }


    @Title("Returns a specific booking based upon booking id provided")
    @Test
    public void getBookingWithSpecificBookingID() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("Mary");
        restPojo.setLastname("Brown");
        restPojo.setTotalprice("721");
        restPojo.setDepositpaid("yes");
        BookingDates bd = new BookingDates();
        bd.setCheckin("12-12-2023");
        bd.setCheckout("12-12-2023");
        restPojo.setBookingdates(bd);

        SerenityRest.given()
                .basePath("/booking")
                .pathParams("id", id)
                .header("Content-Type", "application/json")
                .when()
                .get(EndPoint.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all().statusCode(200);
    }


    @Title("create a new booking")
    @Test
    public void createBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("Mary");
        restPojo.setLastname("Brown");
        restPojo.setTotalprice("721");
        restPojo.setDepositpaid("yes");
        BookingDates bd = new BookingDates();
        bd.setCheckin("12-12-2023");
        bd.setCheckout("12-12-2023");
        restPojo.setBookingdates(bd);
        restPojo.setAdditionalneeds("B & B");
        SerenityRest.given()
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .when()
                .body(restPojo)
                .post()
                .then().log().all().statusCode(200);
    }

    @Title("This will update booking")
    @Test
    public void updateBookingWithID() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("shweta");
        restPojo.setLastname("shah");
        restPojo.setTotalprice("2000");
        restPojo.setDepositpaid("true");
        BookingDates bd = new BookingDates();
        bd.setCheckin("12-12-2023");
        bd.setCheckout("12-12-2023");
        restPojo.setBookingdates(bd);
        restPojo.setAdditionalneeds("B & B");
        SerenityRest.given()
                // .basePath("/booking/2368")
                .headers("Content-Type", "application/json", "Cookie", "token=372a1c9a1abd1f2")
                .pathParams("id", id)
                .when()
                .body(restPojo)
                .put(EndPoint.UPDATE_BOOKING_BY_ID)
                .then().log().all().statusCode(201);
    }

    @Title("This will delete booking")
    @Test
    public void deleteBooking() {

        SerenityRest.given()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("bookingID", id)
                .when()
                .delete("/booking" + EndPoint.DELETE_BOOKING_BY_ID)
                .then()
                .statusCode(201);

        SerenityRest.given()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("bookingID", id)
                .when()
                .get("/booking" + EndPoint.GET_SINGLE_BOOKING_BY_ID)
                .then()
                .statusCode(404);

    }


}
