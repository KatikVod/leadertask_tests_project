package api;

import io.qameta.allure.Step;
import models.CreateEmployeeBodyModel;
import models.CreateProjectBodyModel;

import static api.CreateUserApi.*;
import static io.restassured.RestAssured.given;
import static specs.RequestResponseSpecs.authorisedRequestSpec;
import static specs.RequestResponseSpecs.successful200ResponseSpec;

public class LeaderTaskApi {
    @Step("Создать нового сотрудника")
    public void createEmployee(String name, String email) {
        CreateEmployeeBodyModel employeeData = new CreateEmployeeBodyModel();
        employeeData.setName(name);
        employeeData.setEmail(email);

        given(authorisedRequestSpec(token))
                .body(employeeData)
                .when()
                .post("/emp")
                .then()
                .spec(successful200ResponseSpec);
    }

    @Step("Создать новый проект")
    public void createProject(String uid, String name, String emailCreator) {
        CreateProjectBodyModel projectData = new CreateProjectBodyModel();
        projectData.setUid(uid);
        projectData.setName(name);
        projectData.setEmailCreator(emailCreator);

        given(authorisedRequestSpec(token))
                .body(projectData)
                .when()
                .post("/projects")
                .then()
                .spec(successful200ResponseSpec);
    }
}
