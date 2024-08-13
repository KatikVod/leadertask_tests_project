package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    private final SelenideElement emailField = $(byAttribute("name", "email")),
            continueWithEmailButton = $(".main-button"),
            passwordField = $(byAttribute("name", "password")),
            submitButton = $(byAttribute("type", "submit"));

    public AuthorizationPage openPage() {
        open("");
        return this;
    }

    public AuthorizationPage setEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public AuthorizationPage setPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public AuthorizationPage clickContinueWithEmailButton() {
        continueWithEmailButton.click();
        return this;
    }

    public AuthorizationPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

}

