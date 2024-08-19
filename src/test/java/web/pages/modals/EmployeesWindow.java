package web.pages.modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class EmployeesWindow {

    private final SelenideElement addButton = $(byTagAndText("button", "Добавить"));

    public EmployeesWindow selectEmployee(String employeeEmail) {
        $("#modal-container").$(byText(employeeEmail)).click();
        return this;
    }

    public EmployeesWindow clickAddButton() {
        addButton.click();
        return this;
    }

}
