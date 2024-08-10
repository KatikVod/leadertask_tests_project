package tests.web;

import data.WebTestData;
import helpers.extensions.WithLogin;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.web.MyAccountPage;

import static io.qameta.allure.Allure.step;

@Feature("Профиль пользователя")
@Tag("web")
public class MyAccountTests extends TestBase {
    MyAccountPage myAccountPage = new MyAccountPage();
    WebTestData testData = new WebTestData();

    @Test
    @WithLogin
    @DisplayName("Изменение имени пользователя")
    void changeUserNameTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить имя", () -> {
            myAccountPage.clickChangeNameButton();
        });
        step("Заполнить и сохранить новое имя пользователя", () -> {
            myAccountPage.changeValue(testData.newUserName);
        });
        step("Проверить, что в профиле отображается новое имя пользователя", () -> {
            myAccountPage.checkUserName(testData.newUserName);
        });
    }

    @Test
    @WithLogin
    @DisplayName("Невозможно задать пустое имя пользователя")
    void impossibleToSetEmptyUserNameTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить имя", () -> {
            myAccountPage.clickChangeNameButton();
        });
        step("Очистить имя пользовател и сохранить", () -> {
            myAccountPage.clearValue();
        });
        step("Проверить, что отображается сообщение об ошибке", () -> {
            myAccountPage.checkErrorMessage("Поле не должно быть пустым");
        });
    }

    @Test
    @WithLogin
    @DisplayName("Изменить номер телефона пользователя")
    void changePhoneNumberTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить номер телефона", () -> {
            myAccountPage.clickChangePhoneNumberButton();
        });
        step("Ввести и сохранить новый номер телефона", () -> {
            myAccountPage.changeValue("+7" + testData.userPhoneNumber);
        });
        step("Проверить, что номер телефона в профиле изменен", () -> {
            myAccountPage.checkUserPhoneNumber("+7" + testData.userPhoneNumber);
        });
    }

    @Test
    @WithLogin
    @DisplayName("Невозможно задать номер телефона, состоящий менее чем из 10 цифр")
    void impossibleToSetIncorrectPhoneNumberTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить номер телефона", () -> {
            myAccountPage.clickChangePhoneNumberButton();
        });
        step("Ввести и попытаться сохранить некорректный номер телефона", () -> {
            myAccountPage.changeValue(testData.shortUserPhoneNumber);
        });
        step("Проверить, что отображается сообщение об ошибке", () -> {
            myAccountPage.checkErrorMessage("Некорректный номер телефона");
        });
    }

    @WithLogin
    @CsvFileSource(resources = "/ChooseMenuItem.csv")
    @ParameterizedTest(name = "Для пункта меню {0} должен отображаться заголовок {1}")
    @DisplayName("После перехода в раздел профиля должен отображаться корректный заголовок")
    void chooseMenuItemTest(String menuItemName, String expectedTitle) {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Выбрать пункт меню " + menuItemName, () -> {
            myAccountPage.clickOnMenuItem(menuItemName);
        });
        step("Проверить, что открылся соответствующий раздел профиля", () -> {
            myAccountPage.checkIfPageTitleIsCorrect(expectedTitle);
        });
    }

}
