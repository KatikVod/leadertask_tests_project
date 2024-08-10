package pages.mobile;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.assertj.core.api.Assertions.assertThat;

public class SignUpScreen {
    private final SelenideElement emailField = $(id("com.ashberrysoft.leadertask:id/etRegEmail")),
            loginTitle = $(id("com.ashberrysoft.leadertask:id/tv_login_title"));

    public SignUpScreen checkLoginTitle(String message) {
        assertThat(loginTitle.getText()).isEqualTo(message);
        return this;
    }

    public SignUpScreen checkEmailField(String email) {
        assertThat(emailField.getText()).isEqualTo(email);
        return this;
    }

}
