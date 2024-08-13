package web.extensions;

import api.methods.CreateUserApi;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;

public class LoginExtension implements BeforeEachCallback {
    private static CreateUserApi newUser;

    @Override
    @Step("Создать нового пользователя и авторизоваться им в системе")
    public void beforeEach(ExtensionContext context) {
        newUser = new CreateUserApi();
        open("/favicon.ico");
        localStorage().setItem("user-token", newUser.getToken());
    }

    public static CreateUserApi getCreatedUser() {
        return newUser;
    }
}
