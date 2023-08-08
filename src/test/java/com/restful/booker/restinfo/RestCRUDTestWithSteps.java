package com.restful.booker.restinfo;

import com.restful.booker.testbase.TestBase1;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RestCRUDTestWithSteps extends TestBase1 {

    static String token = "f2bbb73ab3ff34f";

    int id = 1;
    @Steps
    RestSteps restSteps;

    @Title("This will create booking")
    @Test()
    public void createBooking() {
        System.out.println("====================" + token);
        restSteps.creatingBooking("shweta", "shah", "121", "Yes", "B&B","372a1c9a1abd1f2");

       // System.out.println("bookingId :" + id);
        //Assert.assertNotNull(id);
    }

   @Title("This will update booking ")
    @Test()
    public void updateBooking() {
        System.out.println("====================" + token);
       // id = restSteps.updatingBooking("shweta1", "shah", "121", "Yes", "B&B","372a1c9a1abd1f2",14678);

        System.out.println("bookingId :" + id);
        //Assert.assertNotNull(id);
    }




    @Title("This will delete booking")
    @Test
    public void deleteBooking() {

        restSteps.deleteBooking(id, token).statusCode(201);
        restSteps.fetchBooking(id, token).statusCode(404);

    }
}