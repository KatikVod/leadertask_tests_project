package tests.web;

import common.config.AuthConfig;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.AuthorizationPage;
import web.pages.TodayPage;

import static io.qameta.allure.Allure.step;

@Owner("Водолажская Екатерина")
@Feature("Авторизация")
@Story("Авторизация")
@Tag("web")
public class AuthorizationTests extends WebTestBase {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    TodayPage todayPage = new TodayPage();
    AuthConfig config = ConfigFactory.create(AuthConfig.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешная авторизация")
    void changeUserNameTest() {
        step("Открыть страницу авторизации", () -> {
            authorizationPage.openPage();
        });
        step("Ввести email пользователя", () -> {
            authorizationPage.setEmail(config.email());
        });
        step("Нажать на кнопку \"Продолжить с email\"", () -> {
            authorizationPage.clickContinueWithEmailButton();
        });
        step("Ввести пароль пользователя", () -> {
            authorizationPage.setPassword(config.password());
        });
        step("Нажать на кнопку \"Войти\"", () -> {
            authorizationPage.clickSubmitButton();
        });
        step("Проверить, что пользователь авторизован", () -> {
            todayPage.checkCurrentUser(config.userName());
        });
    }

}
