package mobile.screens;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.assertj.core.api.Assertions.assertThat;


public class EnterEmailScreen {
    private final SelenideElement emailField = $(id("com.ashberrysoft.leadertask:id/etRegEmail")),
            continueButton = $(id("com.ashberrysoft.leadertask:id/btnContinue")),
            loginTitle = $(id("com.ashberrysoft.leadertask:id/tv_login_title"));

    public EnterEmailScreen setEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }


    public EnterEmailScreen clickContinueButton() {
        continueButton.click();
        return this;
    }

    public EnterEmailScreen checkLoginTitle(String message) {
        assertThat(loginTitle.getText()).isEqualTo(message);
        return this;
    }
}
