package tests.api;

import api.methods.CreateUserApi;
import api.models.CreateProjectBodyModel;
import api.models.CreateProjectResponseModel;
import api.models.ErrorResponseModel;
import api.models.MessageResponseModel;
import common.data.ApiTestData;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.specs.RequestResponseSpecs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("Водолажская Екатерина")
@Feature("Проект")
@Story("Создание проекта")
@Tag("api")
public class ProjectsTests extends ApiTestBase {

    final ApiTestData testData = new ApiTestData();
    final CreateUserApi newUser = new CreateUserApi();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешное создание проекта с заполнением обязательных атрибутов")
    void createProjectTest() {
        CreateProjectBodyModel projectData = new CreateProjectBodyModel();
        projectData.setUid(testData.projectUid);
        projectData.setEmailCreator(newUser.getEmail());
        projectData.setName(testData.projectName);

        CreateProjectResponseModel response =
                step("Отправить запрос на создание проекта и проверить, что в ответе получен статус 200", () ->
                        given(authorisedRequestLoggingSpec(newUser.getToken()))
                                .body(projectData)
                                .when()
                                .post("/projects")
                                .then()
                                .spec(successful200ResponseSpec)
                                .extract().as(CreateProjectResponseModel.class));

        step("Проверить данные ответа", () -> {
            assertThat(response.getEmailCreator()).isEqualTo(newUser.getEmail());
            assertThat(response.getName()).isEqualTo(testData.projectName);
            assertThat(response.getUid()).isEqualTo(testData.projectUid);
        });
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Невозможно создать проект без названия")
    void createProjectWithEmptyNameTest() {
        CreateProjectBodyModel projectData = new CreateProjectBodyModel();
        projectData.setUid(testData.projectUid);
        projectData.setEmailCreator(newUser.getEmail());

        ErrorResponseModel response =
                step("Отправить запрос на создание проекта и проверить, что в ответе получен статус 500", () ->
                        given(authorisedRequestLoggingSpec(newUser.getToken()))
                                .body(projectData)
                                .when()
                                .post("/projects")
                                .then()
                                .spec(error500ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("empty name"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Невозможно создать проект без uid")
    void createProjectWithEmptyUidTest() {
        CreateProjectBodyModel projectData = new CreateProjectBodyModel();
        projectData.setName("просто название");
        projectData.setEmailCreator(newUser.getEmail());

        ErrorResponseModel response =
                step("Отправить запрос на создание проекта и проверить, что в ответе получен статус 500", () ->
                        given(authorisedRequestLoggingSpec(newUser.getToken()))
                                .body(projectData)
                                .when()
                                .post("/projects")
                                .then()
                                .spec(error500ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("empty uid"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Невозможно создать проект без email_creator")
    void createProjectWithEmptyEmailCreatorTest() {
        CreateProjectBodyModel projectData = new CreateProjectBodyModel();
        projectData.setName("просто название");
        projectData.setUid(testData.projectUid);

        ErrorResponseModel response =
                step("Отправить запрос на создание проекта и проверить, что в ответе получен статус 500", () ->
                        given(authorisedRequestLoggingSpec(newUser.getToken()))
                                .body(projectData)
                                .when()
                                .post("/projects")
                                .then()
                                .spec(error500ResponseSpec)
                                .extract().as(ErrorResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getError()).isEqualTo("email_creator not valid"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Попытка вызвать неподдерживаемый метод DELETE")
    void tryUsingUnallowedMethodTest() {
        MessageResponseModel response =
                step("Отправить запрос на создание проекта и проверить, что в ответе получен статус 405", () ->
                        given(authorisedRequestLoggingSpec(newUser.getToken()))
                                .when()
                                .delete("/projects")
                                .then()
                                .spec(error405ResponseSpec)
                                .extract().as(MessageResponseModel.class));

        step("Проверить ошибку, полученную в ответе", () ->
                assertThat(response.getMessage()).isEqualTo("Запрошенный ресурс не поддерживает HTTP-метод \"DELETE\"."));
    }

}
