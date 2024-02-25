package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.ErrorMessageDto;
import dto.RegistrationBodyDto;
import dto.TokenDto;

import static com.jayway.restassured.RestAssured.given;

public class AuthenticationController extends BaseApi{
    private Response authRegistrationLogin(RegistrationBodyDto registrationBodyDto, String url){
        return given()
                .body(registrationBodyDto)
                .contentType(ContentType.JSON)
                .when()
                .post(baseUrl+url)
                .thenReturn()
                ;
    }
    public int statusCodeResponseAuthRegLogin(RegistrationBodyDto registrationBodyDto, String url){
        return authRegistrationLogin(registrationBodyDto, url).getStatusCode();
    }
    public String tokenResponseAuthRegLogin(RegistrationBodyDto registrationBodyDto, String url){
        return authRegistrationLogin(registrationBodyDto, url).getBody().as(TokenDto.class).getAccessToken();
    }

    public String messageResponseNegativeAuthRegLogin(RegistrationBodyDto registrationBodyDto, String url) {
        return authRegistrationLogin(registrationBodyDto, url).getBody().as(ErrorMessageDto.class).getMessage();
    }
}
