package tests.api;

import data.ApiTestData;
import io.qameta.allure.*;
import models.AuthorizationBodyModel;
import models.AuthorizationResponseModel;
import models.ErrorResponseModel;
import models.MessageResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.CreateUserApi.email;
import static api.CreateUserApi.password;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.RequestResponseSpecs.*;

@Owner("Водолажская Екатерина")
@Feature("Авторизация")
@Story("Проверки эндпоинта /users/authjson")
@Tag("api")
public class AuthorizationTests extends TestBase {

    ApiTestData testData = new ApiTestData();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешнaя авторизация пользователя")
    void successfulUserAuthorizationTest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setLogin(email);
        authData.setPassword(password);
        authData.setSystem("web");
        authData.setTypeDevice("web");

        AuthorizationResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в отсете получен статус 200", () ->
                        given(requestSpec)
                                .body(authData)
                                .when()
                                .post("/users/authjson")
                                .then()
                                .spec(successful200ResponseSpec)
                                .extract().as(AuthorizationResponseModel.class));

        step("Проверить данные ответа", () -> {
            assertThat(response.getAccessToken()).isNotEmpty();
            assertThat(response.getRefreshToken()).isNotEmpty();
        });
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Попытка авторизации с некорректным паролем")
    void authorizationWithWrongPasswordTest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setLogin(email);
        authData.setPassword(testData.wrongPassword);
        authData.setSystem("web");
        authData.setTypeDevice("web");

        ErrorResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в ответе получен статус 400", () ->
                        given(requestSpec)
                                .body(authData)
                                .when()
                                .post("/users/authjson")
                                .then()
                                .spec(error400ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("ivalid auth data"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Попытка авторизации с некорректным email")
    void authorizationWithWrongEmailTest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setLogin(testData.wrongEmail);
        authData.setPassword(password);
        authData.setSystem("web");
        authData.setTypeDevice("web");

        ErrorResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в ответе получен статус 400", () ->
                        given(requestSpec)
                                .body(authData)
                                .when()
                                .post("/users/authjson")
                                .then()
                                .spec(error400ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("user not found"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Попытка авторизации без email")
    void authorizationWithoutEmailTest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setPassword(password);
        authData.setSystem("web");
        authData.setTypeDevice("web");

        ErrorResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в ответе получен статус 400", () ->
                        given(requestSpec)
                                .body(authData)
                                .when()
                                .post("/users/authjson")
                                .then()
                                .spec(error400ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("empty login"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Попытка авторизации без пароля")
    void authorizationWithoutPasswordTest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setLogin(email);
        authData.setSystem("web");
        authData.setTypeDevice("web");

        ErrorResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в ответе получен статус 400", () ->
                        given(requestSpec)
                                .body(authData)
                                .when()
                                .post("/users/authjson")
                                .then()
                                .spec(error400ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("empty password"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Попытка авторизации без атрибута system")
    void authorizationWithoutSystemTest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setLogin(email);
        authData.setPassword(password);
        authData.setTypeDevice("web");

        ErrorResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в ответе получен статус 400", () ->
                        given(requestSpec)
                                .body(authData)
                                .when()
                                .post("/users/authjson")
                                .then()
                                .spec(error400ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("invalid system"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Попытка авторизации без атрибута type_device")
    void authorizationWithoutTypeDeviceTest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setLogin(email);
        authData.setPassword(password);
        authData.setSystem("web");

        ErrorResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в ответе получен статус 400", () ->
                        given(requestSpec)
                                .body(authData)
                                .when()
                                .post("/users/authjson")
                                .then()
                                .spec(error400ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("invalid type_device"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Попытка вызвать неподдерживаемый метод GET")
    void tryUsingUnallowedMethodTest() {
        MessageResponseModel response =
                step("Отправить запрос на авторизацию и проверить, что в ответе получен статус 405", () ->
                        given()
                                .when()
                                .get("/users/authjson")
                                .then()
                                .spec(error405ResponseSpec)
                                .extract().as(MessageResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getMessage()).isEqualTo("Запрошенный ресурс не поддерживает HTTP-метод \"GET\"."));
    }
}
