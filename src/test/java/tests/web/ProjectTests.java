package tests.web;

import api.LeaderTaskApi;
import data.WebTestData;
import helpers.extensions.WithLogin;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.web.ProjectPage;

import static api.CreateUserApi.email;
import static io.qameta.allure.Allure.step;

@Feature("Действия с проектом")
@Tag("web")
public class ProjectTests extends TestBase {
    LeaderTaskApi apiSteps = new LeaderTaskApi();
    ProjectPage projectPage = new ProjectPage();
    WebTestData testData;

    @BeforeEach
    void beforeEach() {
        testData = new WebTestData();
        apiSteps.createProject(testData.projectUid, testData.projectName, email);
    }

    @Test
    @WithLogin
    @DisplayName("Добавить в проект сотрудника")
    void addEmployeeToProjectTest() {

        step("Создать нового сотрудника через api", () -> {
            apiSteps.createEmployee(testData.employeeName, testData.employeeEmail);
        });
        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку Добавить сотрудника", () -> {
            projectPage.clickAddEmployeeButton();
        });
        step("Добавить сотрудника в проект", () -> {
            projectPage.addEmployee(testData.employeeEmail);
        });
        step("Проверить, что сотрудник отображается в свойствах проекта", () -> {
            projectPage.clickActionsButton();
            projectPage.clickProjectPropertiesButton();
            projectPage.checkEmployeeIsAdded(testData.employeeEmail, testData.employeeName);
        });
    }

    @Test
    @WithLogin
    @DisplayName("Добавить в проект задачу")
    void addTaskToProjectTest() {

        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Создать новую задачу", () -> {
            projectPage.createNewTask("Первая задача");
        });
        step("Проверить, что задача отображается в проекте", () -> {
            projectPage.checkTaskName("Первая задача");
        });
    }

    @Test
    @WithLogin
    @DisplayName("Создать подпроект")
    void createSubprojectTest() {

        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку действий с проектом", () -> {
            projectPage.clickActionsButton();
        });
        step("Нажать на кнопку Создать подпроект", () -> {
            projectPage.clickCreateSubprojectButton();
        });
        step("Заполнить название подпроекта и сохранить", () -> {
            projectPage.createSubproject("Подпроект");
        });
        step("Проверить, что подпроект появился в дереве проектов", () -> {
            projectPage.checkProjectExist("Подпроект");
        });
    }

    @Test
    @WithLogin
    @DisplayName("Невозможно создать подпроект с пустым названием")
    void impossibleToCreateSubprojectWithEmptyNameTest() {

        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку действий с проектом", () -> {
            projectPage.clickActionsButton();
        });
        step("Нажать на кнопку Создать подпроект", () -> {
            projectPage.clickCreateSubprojectButton();
        });
        step("Заполнить название подпроекта и сохранить", () -> {
            projectPage.createSubproject("");
        });
        step("Проверить, что отображается ошибка", () -> {
            projectPage.checkErrorMessage("Поле не должно быть пустым");
        });

    }

    @Test
    @WithLogin
    @DisplayName("Удалить проект")
    void deleteProjectTest() {

        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку действий с проектом", () -> {
            projectPage.clickActionsButton();
        });
        step("Нажать на кнопку Удалить проект", () -> {
            projectPage.clickDeleteProject(testData.projectName);
        });
        step("Подтвердить удаление проекта", () -> {
            projectPage.confirmDeleteProject();
        });
        step("Проверить, что проект не отображается в дереве проектов", () -> {
            projectPage.checkProjectIsDeleted(testData.projectName);
        });
    }

    @Test
    @WithLogin
    @DisplayName("Отменить удаление проекта")
    void cancelProjectDeleteTest() {

        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку действий с проектом", () -> {
            projectPage.clickActionsButton();
        });
        step("Нажать на кнопку Удалить проект", () -> {
            projectPage.clickDeleteProject(testData.projectName);
        });
        step("В окне подтверждения отменить удаление", () -> {
            projectPage.cancelDeleteProject();
        });
        step("Проверить, что проект отображается в дереве проектов", () -> {
            projectPage.checkProjectExist(testData.projectName);
        });
    }
}
