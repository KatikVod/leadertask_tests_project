package api.methods;

import api.models.CreateEmployeeBodyModel;
import api.models.CreateProjectBodyModel;
import io.qameta.allure.Step;

import static api.specs.RequestResponseSpecs.authorisedRequestLoggingSpec;
import static api.specs.RequestResponseSpecs.successful200ResponseSpec;
import static io.restassured.RestAssured.given;

public class LeaderTaskApiSteps {
    @Step("Создать нового сотрудника")
    public void createEmployee(String name, String email, String token) {
        CreateEmployeeBodyModel employeeData = new CreateEmployeeBodyModel();
        employeeData.setName(name);
        employeeData.setEmail(email);

        given(authorisedRequestLoggingSpec(token))
                .body(employeeData)
                .when()
                .post("/emp")
                .then()
                .spec(successful200ResponseSpec);
    }

    @Step("Создать новый проект")
    public void createProject(String uid, String name, String emailCreator, String token) {
        CreateProjectBodyModel projectData = new CreateProjectBodyModel();
        projectData.setUid(uid);
        projectData.setName(name);
        projectData.setEmailCreator(emailCreator);

        given(authorisedRequestLoggingSpec(token))
                .body(projectData)
                .when()
                .post("/projects")
                .then()
                .spec(successful200ResponseSpec);
    }
}
