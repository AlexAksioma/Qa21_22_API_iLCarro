package rest;

import api.CarController;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllUserCars extends CarController {

    @Test
    public void getAllUserCarPositiveTest(){
        Assert.assertEquals(statusCodeGetAllUserCar(),200);
    }
}
