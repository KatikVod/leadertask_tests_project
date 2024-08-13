package tests.mobile;

import common.config.AuthConfig;
import common.data.MobileTestData;
import io.qameta.allure.*;
import mobile.screens.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Owner("Водолажская Екатерина")
@Feature("Авторизация")
@Story("Флоу авторизации в мобильном приложении")
@Tag("mobile")
public class AuthorizationFlowTests extends MobileTestBase {
    WelcomeScreen welcomeScreen = new WelcomeScreen();
    EnterEmailScreen enterEmailScreen = new EnterEmailScreen();
    SignUpScreen signUpScreen = new SignUpScreen();
    SignInScreen signInScreen = new SignInScreen();
    TodayScreen todayScreen = new TodayScreen();
    MobileTestData testData = new MobileTestData();
    AuthConfig config = ConfigFactory.create(AuthConfig.class);

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешная авторизация")
    void successfulAuthorizationTest() {
        step("Не выдавать разрешения", () -> {
            welcomeScreen.clickPermissionDenyButton();
        });
        step("Нажать на кнопку Continue with email", () -> {
            welcomeScreen.clickContinueWithEmailButton();
        });
        step("Проверить, что произошел переход на экран ввода email", () -> {
            enterEmailScreen.checkLoginTitle("Enter email");
        });
        step("Ввести email и нажать на кнопку Continue", () -> {
            enterEmailScreen.setEmail(config.email())
                    .clickContinueButton();
        });
        step("Проверить, что произошел переход на экран авторизации", () -> {
            signInScreen.checkLoginTitle("Sign in")
                    .checkEmailField(config.email());
        });
        step("Ввести пароль, нажать Sign in", () -> {
            signInScreen.setPassword(config.password())
                    .clickContinueButton();
        });
        step("Проверить, что произошел вход в приложение", () -> {
            todayScreen.checkToolbarText("Today");
        });
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка возврата на начальный экран")
    void returnToEmailScreenTest() {
        step("Не выдавать разрешения", () -> {
            welcomeScreen.clickPermissionDenyButton();
        });
        step("Нажать на кнопку Continue with email", () -> {
            welcomeScreen.clickContinueWithEmailButton();
        });
        step("Проверить, что произошел переход на экран ввода email", () -> {
            enterEmailScreen.checkLoginTitle("Enter email");
        });
        step("Ввести email и нажать на кнопку Continue", () -> {
            enterEmailScreen.setEmail(config.email())
                    .clickContinueButton();
        });
        step("Нажать на \"крестик\"", () -> {
            signInScreen.clickBackToLoginButton();
        });
        step("Проверить, что произошел возврат на начальный экран", () -> {
            welcomeScreen.checkIfLogoIsDisplayed()
                    .checkIfContinueWithEmailButtonIsDisplayed();
        });
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Авторизация с новым email, переход к регистрации")
    void authorizationWithNewEmailTest() {
        step("Не выдавать разрешения", () -> {
            welcomeScreen.clickPermissionDenyButton();
        });
        step("Нажать на кнопку Continue with email", () -> {
            welcomeScreen.clickContinueWithEmailButton();
        });
        step("Ввести email, нажать Continue", () -> {
            enterEmailScreen.setEmail(testData.unknownEmail)
                    .clickContinueButton();
        });
        step("Проверить, что произошел переход на экран регистрации", () -> {
            signUpScreen.checkLoginTitle("Create an Account")
                    .checkEmailField(testData.unknownEmail);
        });
    }
}