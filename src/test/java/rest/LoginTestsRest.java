package rest;

import api.AuthenticationController;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AuthenticationController {
    String userName, password;
    String url = "/v1/user/login/usernamepassword";

    @BeforeClass
    public void registrationUser(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobaggins@mail.com")
                .password("QAzxc!_"+i)
                .firstName("bilbo")
                .lastName("baggins")
                .build();
        System.out.println(registrationBodyDto.getUsername());
        System.out.println(registrationBodyDto.getPassword());
        System.out.println("status code --> "+statusCodeResponseAuthRegLogin(registrationBodyDto,
                "/v1/user/registration/usernamepassword"));
        userName = registrationBodyDto.getUsername();
        password = registrationBodyDto.getPassword();
    }

    @Test
    public void loginPositiveTest(){
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(userName)
                .password(password)
                .build();
        Assert.assertEquals(statusCodeResponseAuthRegLogin(registrationBodyDto, url),200);
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(userName)
                .password("1111111")
                .build();
        Assert.assertEquals(messageResponseNegativeAuthRegLogin(registrationBodyDto, url),
                "Login or Password incorrect");
    }


}
