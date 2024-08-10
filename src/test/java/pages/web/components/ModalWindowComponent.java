package pages.web.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class ModalWindowComponent {

    private final SelenideElement input = $("#modal-container").$("input"),
            saveButton = $(byTagAndText("button", "Сохранить")),
            error = $("#modal-container").$(".text-red-500");

    public ModalWindowComponent setValue(String value) {
        input.setValue(value);
        return this;
    }

    public ModalWindowComponent clearValue() {
        input.sendKeys(Keys.CONTROL + "A");
        input.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public ModalWindowComponent changeValue(String newValue) {
        clearValue();
        setValue(newValue);
        return this;
    }

    public ModalWindowComponent clickSaveButton() {
        saveButton.click();
        return this;
    }

    public ModalWindowComponent checkErrorMessage(String message) {
        assertThat(error.getText()).isEqualTo(message);
        return this;
    }

}
