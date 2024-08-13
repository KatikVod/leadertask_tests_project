package web.pages.modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmWindow {

    private final SelenideElement yesButton = $(byTagAndText("button", "Да")),
            noButton = $(byTagAndText("button", "Нет")),
            windowLabel = $("#modal-container").$(".flex.w-auto"),
            windowText = $("#modal-container").$(".flex.items-stretch");

    public ConfirmWindow clickYesButton() {
        yesButton.click();
        return this;
    }

    public ConfirmWindow clickNoButton() {
        noButton.click();
        return this;
    }

    public ConfirmWindow checkWindowLabel(String label) {
        assertThat(windowLabel.getText()).isEqualTo(label);
        return this;
    }

    public ConfirmWindow checkWindowText(String text) {
        assertThat(windowText.getText()).isEqualTo(text);
        return this;
    }
}
