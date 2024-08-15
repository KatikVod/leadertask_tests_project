package api.methods;

import api.models.CreateUserBodyModel;
import api.models.CreateUserResponseModel;
import common.data.CreateUserData;
import lombok.Getter;

import static api.specs.RequestResponseSpecs.defaultLoggingRequestSpec;
import static api.specs.RequestResponseSpecs.successful200ResponseSpec;
import static io.restassured.RestAssured.given;

@Getter
public class CreateUserApi {
    private final CreateUserData newUserData;
    private String token;
    private final String email, password;

    public CreateUserApi() {
        newUserData = new CreateUserData();
        email = getNewUserData().userEmail;
        password = getNewUserData().userPassword;
        setAuthData();
    }

    private CreateUserResponseModel createUserRequest() {
        CreateUserBodyModel createUserData = new CreateUserBodyModel();
        createUserData.setEmail(getNewUserData().userEmail);
        createUserData.setPassword(getNewUserData().userPassword);
        createUserData.setName(getNewUserData().userName);
        createUserData.setPhone(getNewUserData().userPhoneNumber);

        return given(defaultLoggingRequestSpec)
                .body(createUserData)
                .when()
                .post("/users/new")
                .then()
                .spec(successful200ResponseSpec)
                .extract().as(CreateUserResponseModel.class);
    }

    private void setAuthData() {
        CreateUserResponseModel authResponse = createUserRequest();
        if (authResponse.getAccessToken() != null) {
            token = authResponse.getAccessToken();
        } else {
            throw new IllegalStateException("Failed to create user: no access token");
        }
    }
}