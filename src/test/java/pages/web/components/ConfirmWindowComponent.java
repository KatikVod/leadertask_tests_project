package pages.web.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmWindowComponent {

    private final SelenideElement yesButton = $(byTagAndText("button", "Да")),
            noButton = $(byTagAndText("button", "Нет")),
            windowLabel = $("#modal-container").$(".flex.w-auto"),
            windowText = $("#modal-container").$(".flex.items-stretch");

    public ConfirmWindowComponent clickYesButton() {
        yesButton.click();
        return this;
    }

    public ConfirmWindowComponent clickNoButton() {
        noButton.click();
        return this;
    }

    public ConfirmWindowComponent checkWindowLabel(String label) {
        assertThat(windowLabel.getText()).isEqualTo(label);
        return this;
    }

    public ConfirmWindowComponent checkWindowText(String text) {
        assertThat(windowText.getText()).isEqualTo(text);
        return this;
    }
}
