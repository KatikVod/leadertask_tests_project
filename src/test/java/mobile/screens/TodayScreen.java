package mobile.screens;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.assertj.core.api.Assertions.assertThat;

public class TodayScreen {

    private final SelenideElement toolbarText = $(id("com.ashberrysoft.leadertask:id/toolbar_text_name"));

    public TodayScreen checkToolbarText(String text) {
        assertThat(toolbarText.getText()).contains(text);
        return this;
    }
}
