package api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import dto.CarDto;
import dto.RegistrationBodyDto;
import dto.TokenDto;
import org.testng.annotations.BeforeSuite;

import static com.jayway.restassured.RestAssured.given;

public class CarController extends BaseApi{

    String token ="";
    RequestSpecification requestSpecification;
    @BeforeSuite
    public void getTokenForCarController(){
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(userNameRegistered)
                .password(passwordRegistered)
                .build();
        token = given()
                .body(registrationBodyDto)
                .contentType(ContentType.JSON)
                .when()
                .post(baseUrl+"/v1/user/login/usernamepassword")
                .thenReturn()
                .getBody()
                .as(TokenDto.class)
                .getAccessToken();
        System.out.println(token);
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setContentType(ContentType.JSON)
                .build();
    }
    private Response getAllUserCarResponse(){
        return given()
                .spec(requestSpecification)    //add contentType, header Authorization
                .when()
                .get(baseUrl+"/v1/cars/my")
                .thenReturn();
    }
    public int statusCodeGetAllUserCar(){
        return getAllUserCarResponse().getStatusCode();
    }

    private Response addNewCarResponse(CarDto carDto){
        return given()
                .body(carDto)
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post(baseUrl+"/v1/cars")
                .thenReturn()
                ;
    }

    public int statusCodeAddNewCarResponse(CarDto carDto){
        return addNewCarResponse(carDto).getStatusCode();
    }
}
