package api;

import data.CreateUserData;
import models.CreateUserBodyModel;
import models.CreateUserResponseModel;

import static io.restassured.RestAssured.given;
import static specs.RequestResponseSpecs.requestSpec;
import static specs.RequestResponseSpecs.successful200ResponseSpec;

public class CreateUserApi {
    private static CreateUserData newUserData;
    public static String token, email, password;

    public static CreateUserResponseModel createUserRequest() {
        CreateUserBodyModel createUserData = new CreateUserBodyModel();
        newUserData = new CreateUserData();
        createUserData.setEmail(newUserData.userEmail);
        createUserData.setPassword(newUserData.userPassword);
        createUserData.setName(newUserData.userName);
        createUserData.setPhone(newUserData.userPhoneNumber);

        return given(requestSpec)
                .body(createUserData)
                .when()
                .post("/users/new")
                .then()
                .spec(successful200ResponseSpec)
                .extract().as(CreateUserResponseModel.class);
    }

    public static void setAuthData() {
        CreateUserResponseModel authResponse = createUserRequest();
        token = authResponse.getAccessToken();
        email = newUserData.userEmail;
        password = newUserData.userPassword;
    }
}