package tests.web;

import api.methods.LeaderTaskApiSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.extensions.LoginExtension;
import web.extensions.WithLogin;
import web.pages.ProjectPage;
import web.pages.modals.ConfirmWindow;
import web.pages.modals.EmployeesWindow;
import web.pages.modals.ModalWindow;

import static io.qameta.allure.Allure.step;

@Owner("Водолажская Екатерина")
@Feature("Проект")
@Tag("web")
public class ProjectTests extends WebTestBase {
    final LeaderTaskApiSteps apiSteps = new LeaderTaskApiSteps();
    final ProjectPage projectPage = new ProjectPage();
    final ModalWindow modalWindow = new ModalWindow();
    final ConfirmWindow confirmWindow = new ConfirmWindow();
    final EmployeesWindow employeesWindow = new EmployeesWindow();

    @Test
    @WithLogin
    @Severity(SeverityLevel.CRITICAL)
    @Story("Действия с проектом")
    @DisplayName("Добавить в проект сотрудника")
    void addEmployeeToProjectTest() {
        step("Создать проект и нового сотрудника через api", () -> {
            apiSteps.createProject(testData.projectUid, testData.projectName, LoginExtension.getCreatedUser().getEmail(), LoginExtension.getCreatedUser().getToken());
            apiSteps.createEmployee(testData.employeeName, testData.employeeEmail, LoginExtension.getCreatedUser().getToken());
        });
        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку Добавить сотрудника", () -> {
            projectPage.clickAddEmployeeButton();
        });
        step("Добавить сотрудника в проект", () -> {
            employeesWindow.selectEmployee(testData.employeeEmail)
                    .clickAddButton();
        });
        step("Проверить, что сотрудник отображается в свойствах проекта", () -> {
            projectPage.clickActionsButton()
                    .clickProjectPropertiesButton()
                    .checkEmployeeIsAdded(testData.employeeEmail, testData.employeeName);
        });
    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.BLOCKER)
    @Story("Задачи")
    @DisplayName("Добавить в проект задачу")
    void addTaskToProjectTest() {
        step("Создать проект через api", () -> {
            apiSteps.createProject(testData.projectUid, testData.projectName, LoginExtension.getCreatedUser().getEmail(), LoginExtension.getCreatedUser().getToken());
        });
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
    @Severity(SeverityLevel.CRITICAL)
    @Story("Подпроект")
    @DisplayName("Создать подпроект")
    void createSubprojectTest() {
        step("Создать проект через api", () -> {
            apiSteps.createProject(testData.projectUid, testData.projectName, LoginExtension.getCreatedUser().getEmail(), LoginExtension.getCreatedUser().getToken());
        });
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
            modalWindow.setValue("Подпроект")
                    .clickSaveButton();
        });
        step("Проверить, что подпроект появился в дереве проектов", () -> {
            projectPage.checkProjectExist("Подпроект");
        });
    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.NORMAL)
    @Story("Подпроект")
    @DisplayName("Невозможно создать подпроект с пустым названием")
    void impossibleToCreateSubprojectWithEmptyNameTest() {
        step("Создать проект через api", () -> {
            apiSteps.createProject(testData.projectUid, testData.projectName, LoginExtension.getCreatedUser().getEmail(), LoginExtension.getCreatedUser().getToken());
        });
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
            modalWindow.setValue("")
                    .clickSaveButton();
        });
        step("Проверить, что отображается ошибка", () -> {
            modalWindow.checkErrorMessage("Поле не должно быть пустым");
        });

    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.CRITICAL)
    @Story("Действия с проектом")
    @DisplayName("Удалить проект")
    void deleteProjectTest() {
        step("Создать проект через api", () -> {
            apiSteps.createProject(testData.projectUid, testData.projectName, LoginExtension.getCreatedUser().getEmail(), LoginExtension.getCreatedUser().getToken());
        });
        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку действий с проектом", () -> {
            projectPage.clickActionsButton();
        });
        step("Нажать на кнопку Удалить проект", () -> {
            projectPage.clickDeleteProjectButton();
            confirmWindow.checkWindowLabel("Удалить проект")
                    .checkWindowText("Вы действительно хотите удалить проект " + testData.projectName + "?");
        });
        step("Подтвердить удаление проекта", () -> {
            confirmWindow.clickYesButton();
        });
        step("Проверить, что проект удален", () -> {
            projectPage.checkProjectIsDeleted();
        });
    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.CRITICAL)
    @Story("Действия с проектом")
    @DisplayName("Отменить удаление проекта")
    void cancelProjectDeleteTest() {
        step("Создать проект через api", () -> {
            apiSteps.createProject(testData.projectUid, testData.projectName, LoginExtension.getCreatedUser().getEmail(), LoginExtension.getCreatedUser().getToken());
        });
        step("Открыть страницу проекта " + testData.projectName, () -> {
            projectPage.openPage(testData.projectUid, testData.projectName);
        });
        step("Нажать на кнопку действий с проектом", () -> {
            projectPage.clickActionsButton();
        });
        step("Нажать на кнопку Удалить проект", () -> {
            projectPage.clickDeleteProjectButton();
            confirmWindow.checkWindowLabel("Удалить проект")
                    .checkWindowText("Вы действительно хотите удалить проект " + testData.projectName + "?");
        });
        step("В окне подтверждения отменить удаление", () -> {
            confirmWindow.clickNoButton();
        });
        step("Проверить, что проект отображается в дереве проектов", () -> {
            projectPage.checkProjectExist(testData.projectName);
        });
    }
}
