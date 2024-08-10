package pages.mobile;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.assertj.core.api.Assertions.assertThat;

public class WelcomeScreen {
    private final SelenideElement permissionDenyButton = $(id("com.android.permissioncontroller:id/permission_deny_button")),
            continueWithEmailButton = $(id("com.ashberrysoft.leadertask:id/l_continue_email")),
            logo = $(id("com.ashberrysoft.leadertask:id/logo"));

    public WelcomeScreen clickPermissionDenyButton() {
        permissionDenyButton.click();
        return this;
    }

    public WelcomeScreen clickContinueWithEmailButton() {
        continueWithEmailButton.click();
        return this;
    }

    public WelcomeScreen checkIfLogoIsDisplayed() {
        assertThat(logo.isDisplayed()).isEqualTo(true);
        return this;
    }

    public WelcomeScreen checkIfContinueWithEmailButtonIsDisplayed() {
        assertThat(continueWithEmailButton.isDisplayed()).isEqualTo(true);
        return this;
    }
}
