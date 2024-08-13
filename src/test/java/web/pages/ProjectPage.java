package web.pages;

import com.codeborne.selenide.SelenideElement;
import web.pages.components.MainMenuComponent;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
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

    final MainMenuComponent mainMenu = new MainMenuComponent();

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

    public ProjectPage clickDeleteProjectButton() {
        deleteProjectButton.click();
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

    public ProjectPage checkProjectExist(String projectName) {
        mainMenu.checkProjectExist(projectName);
        return this;
    }

    public ProjectPage checkProjectIsDeleted() {
        mainMenu.checkProjectTreeNotExist();
        assertThat(getWebDriver().getCurrentUrl()).isEqualTo("https://www.leadertask.ru/web/tasks/today");
        return this;
    }

    public ProjectPage checkEmployeeIsAdded(String employeeEmail, String employeeName) {
        assertThat(rightMenu.getText()).contains(employeeEmail, employeeName);
        return this;
    }
}
