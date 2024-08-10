package pages.web;

import com.codeborne.selenide.SelenideElement;
import pages.web.components.ConfirmWindowComponent;
import pages.web.components.EmployeesWindowComponent;
import pages.web.components.MainMenuComponent;
import pages.web.components.ModalWindowComponent;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectPage {

    private final SelenideElement navBarProjects = $("#NavBarProjects"),
            newTaskField = $("#task"),
            addEmployeeButton = $("#NavBarProjects").$(byText("Добавить сотрудника")),
            taskName = $(".taskName"),
            actionsButton = $("#NavBarProjects").$(".el-tooltip__trigger"),
            deleteProjectButton = $(byText("Удалить проект")),
            createSubprojectButton = $(byAttribute("aria-hidden", "false")).$(byText("Создать подпроект")),
            projectPropertiesButton = $(byText("Свойства проекта")),
            rightMenu = $("#aside-right");

    ModalWindowComponent modalWindow = new ModalWindowComponent();
    MainMenuComponent mainMenu = new MainMenuComponent();
    ConfirmWindowComponent confirmWindow = new ConfirmWindowComponent();
    EmployeesWindowComponent employeesWindow = new EmployeesWindowComponent();

    public ProjectPage openPage(String projectUid, String projectName) {
        open("/project/" + projectUid);
        assertThat(navBarProjects.getText()).contains(projectName);
        return this;
    }

    public ProjectPage createNewTask(String taskName) {
        newTaskField.setValue(taskName).pressEnter();
        return this;
    }

    public ProjectPage checkTaskName(String name) {
        assertThat(taskName.getText()).contains(name);
        return this;
    }

    public ProjectPage clickAddEmployeeButton() {
        addEmployeeButton.click();
        return this;
    }

    public ProjectPage clickActionsButton() {
        actionsButton.click();
        return this;
    }

    public ProjectPage clickDeleteProject(String projectName) {
        deleteProjectButton.click();
        confirmWindow.checkWindowLabel("Удалить проект")
                .checkWindowText("Вы действительно хотите удалить проект " + projectName + "?");
        return this;

    }

    public ProjectPage confirmDeleteProject() {
        confirmWindow.clickYesButton();
        return this;
    }

    public ProjectPage cancelDeleteProject() {
        confirmWindow.clickNoButton();
        return this;
    }


    public ProjectPage clickCreateSubprojectButton() {
        createSubprojectButton.click();
        return this;
    }

    public ProjectPage clickProjectPropertiesButton() {
        projectPropertiesButton.click();
        return this;
    }

    public ProjectPage createSubproject(String subprojectName) {
        modalWindow.setValue(subprojectName);
        modalWindow.clickSaveButton();
        return this;
    }

    public ProjectPage checkErrorMessage(String message) {
        modalWindow.checkErrorMessage(message);
        return this;
    }

    public ProjectPage checkProjectExist(String projectName) {
        mainMenu.checkProjectExist(projectName);
        return this;
    }

    public ProjectPage checkProjectIsDeleted(String projectName) {
        mainMenu.checkProjectNotExist(projectName);
        assertThat(getWebDriver().getCurrentUrl()).isEqualTo("https://www.leadertask.ru/web/tasks/today");
        return this;
    }

    public ProjectPage addEmployee(String employeeEmail) {
        employeesWindow.selectEmployee(employeeEmail);
        employeesWindow.clickAddButton();
        return this;
    }

    public ProjectPage checkEmployeeIsAdded(String employeeEmail, String employeeName) {
        assertThat(rightMenu.getText()).contains(employeeEmail, employeeName);
        return this;
    }
}
