package api.methods;

import api.models.CreateUserBodyModel;
import api.models.CreateUserResponseModel;
import common.data.CreateUserData;
import lombok.Getter;
import lombok.Setter;

import static api.specs.RequestResponseSpecs.defaultLoggingRequestSpec;
import static api.specs.RequestResponseSpecs.successful200ResponseSpec;
import static io.restassured.RestAssured.given;

@Getter
@Setter
public class CreateUserApi {
    private final CreateUserData newUserData;
    private String token, email, password;

    public CreateUserApi() {
        newUserData = new CreateUserData();
        this.setEmail(getNewUserData().userEmail);
        this.setPassword(getNewUserData().userPassword);
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
            this.setToken(authResponse.getAccessToken());
        } else {
            throw new IllegalStateException("Failed to create user: no access token");
        }
    }
}