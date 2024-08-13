package web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyAccountPage {
    private final SelenideElement changeNameButton = $(byText("Изменить имя")),
            changePhoneNumberButton = $(byText("Изменить номер телефона")),
            currentPageTitle = $(".grid"),
            userName = $(byText("Имя")).sibling(0),
            userPhoneNumber = $(byText("Телефон")).sibling(0);
    private final ElementsCollection menuItems = $$(".font-roboto.font-medium.truncate");

    public MyAccountPage openPage() {
        open("/account/myaccount");
        checkIfPageTitleIsCorrect("Аккаунт");
        return this;
    }

    public MyAccountPage clickChangeNameButton() {
        changeNameButton.click();
        return this;
    }

    public MyAccountPage clickChangePhoneNumberButton() {
        changePhoneNumberButton.click();
        return this;
    }

    public MyAccountPage checkUserName(String name) {
        userName.shouldHave(text(name));
        return this;
    }

    public MyAccountPage checkUserPhoneNumber(String phoneNumber) {
        userPhoneNumber.shouldHave(text(phoneNumber));
        return this;
    }

    public MyAccountPage clickOnMenuItem(String menuItemName) {
        menuItems.findBy(text(menuItemName)).click();
        return this;
    }

    public MyAccountPage checkIfPageTitleIsCorrect(String expectedTitle) {
        currentPageTitle.shouldHave(text(expectedTitle));
        return this;
    }
}
