package helpers.extensions;

import api.CreateUserApi;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static api.CreateUserApi.token;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;

public class LoginExtension implements BeforeEachCallback {
    @Override
    @Step("Создать нового пользователя и авторизоваться им в системе")
    public void beforeEach(ExtensionContext context) {
        CreateUserApi.setAuthData();
        open("/favicon.ico");
        localStorage().setItem("user-token", token);
    }
}
