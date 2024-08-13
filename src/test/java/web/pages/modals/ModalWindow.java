package web.pages.modals;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class ModalWindow {

    private final SelenideElement input = $("#modal-container").$("input"),
            saveButton = $(byTagAndText("button", "Сохранить")),
            error = $("#modal-container").$(".text-red-500");

    public ModalWindow setValue(String value) {
        input.setValue(value);
        return this;
    }

    public ModalWindow clearValue() {
        input.sendKeys(Keys.CONTROL + "A");
        input.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public ModalWindow changeValue(String newValue) {
        clearValue();
        setValue(newValue);
        return this;
    }

    public ModalWindow clickSaveButton() {
        saveButton.click();
        return this;
    }

    public ModalWindow checkErrorMessage(String message) {
        assertThat(error.getText()).isEqualTo(message);
        return this;
    }

}
