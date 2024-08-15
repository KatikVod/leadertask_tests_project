package tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import web.extensions.WithLogin;
import web.pages.MyAccountPage;
import web.pages.modals.ModalWindow;

import static io.qameta.allure.Allure.step;

@Owner("Водолажская Екатерина")
@Feature("Профиль пользователя")
@Tag("web")
public class MyAccountTests extends WebTestBase {
    final MyAccountPage myAccountPage = new MyAccountPage();
    final ModalWindow modalWindow = new ModalWindow();

    @Test
    @WithLogin
    @Severity(SeverityLevel.CRITICAL)
    @Story("Аккаунт и лицензия")
    @DisplayName("Изменение имени пользователя")
    void changeUserNameTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить имя", () -> {
            myAccountPage.clickChangeNameButton();
        });
        step("Заполнить и сохранить новое имя пользователя", () -> {
            modalWindow.clearValue()
                    .setValue(testData.newUserName)
                    .clickSaveButton();
        });
        step("Проверить, что в профиле отображается новое имя пользователя", () -> {
            myAccountPage.checkUserName(testData.newUserName);
        });
    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.NORMAL)
    @Story("Аккаунт и лицензия")
    @DisplayName("Невозможно задать пустое имя пользователя")
    void impossibleToSetEmptyUserNameTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить имя", () -> {
            myAccountPage.clickChangeNameButton();
        });
        step("Очистить имя пользовател и сохранить", () -> {
            modalWindow.clearValue()
                    .clickSaveButton();
        });
        step("Проверить, что отображается сообщение об ошибке", () -> {
            modalWindow.checkErrorMessage("Поле не должно быть пустым");
        });
    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.CRITICAL)
    @Story("Аккаунт и лицензия")
    @DisplayName("Изменить номер телефона пользователя")
    void changePhoneNumberTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить номер телефона", () -> {
            myAccountPage.clickChangePhoneNumberButton();
        });
        step("Ввести и сохранить новый номер телефона", () -> {
            modalWindow.clearValue()
                    .setValue("+7" + testData.userPhoneNumber)
                    .clickSaveButton();
        });
        step("Проверить, что номер телефона в профиле изменен", () -> {
            myAccountPage.checkUserPhoneNumber("+7" + testData.userPhoneNumber);
        });
    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.NORMAL)
    @Story("Аккаунт и лицензия")
    @DisplayName("Невозможно задать номер телефона, состоящий менее чем из 10 цифр")
    void impossibleToSetIncorrectPhoneNumberTest() {
        step("Открыть страницу Аккаунт", () -> {
            myAccountPage.openPage();
        });
        step("Нажать на кнопку Изменить номер телефона", () -> {
            myAccountPage.clickChangePhoneNumberButton();
        });
        step("Ввести и попытаться сохранить некорректный номер телефона", () -> {
            modalWindow.clearValue()
                    .setValue(testData.shortUserPhoneNumber)
                    .clickSaveButton();
        });
        step("Проверить, что отображается сообщение об ошибке", () -> {
            modalWindow.checkErrorMessage("Некорректный номер телефона");
        });
    }

    @WithLogin
    @Severity(SeverityLevel.NORMAL)
    @CsvFileSource(resources = "/files/ChooseMenuItem.csv")
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
