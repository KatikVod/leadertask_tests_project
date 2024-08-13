package web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class TodayPage {

    private final SelenideElement currentUser = $(byAttribute("href", "/web/account"));

    public TodayPage checkCurrentUser(String userName) {
        assertThat(currentUser.getText()).isEqualTo(userName);
        return this;
    }

}
