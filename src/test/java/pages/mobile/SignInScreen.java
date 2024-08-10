package pages.mobile;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.assertj.core.api.Assertions.assertThat;

public class SignInScreen {
    private final SelenideElement emailField = $(id("com.ashberrysoft.leadertask:id/etRegEmail")),
            passwordField = $(id("com.ashberrysoft.leadertask:id/reg_pass")),
            loginTitle = $(id("com.ashberrysoft.leadertask:id/tv_login_title")),
            signInButton = $(id("com.ashberrysoft.leadertask:id/btnContinue")),
            backToLoginButton = $(id("com.ashberrysoft.leadertask:id/back_to_login"));

    public SignInScreen setPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public SignInScreen clickContinueButton() {
        signInButton.click();
        return this;
    }

    public SignInScreen checkLoginTitle(String message) {
        assertThat(loginTitle.getText()).isEqualTo(message);
        return this;
    }

    public SignInScreen checkEmailField(String email) {
        assertThat(emailField.getText()).isEqualTo(email);
        return this;
    }

    public SignInScreen clickBackToLoginButton() {
        backToLoginButton.click();
        return this;
    }

}
