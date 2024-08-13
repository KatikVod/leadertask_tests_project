package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import mobile.drivers.BrowserstackDriver;
import mobile.drivers.EmulatorMobileDriver;
import mobile.drivers.RealMobileDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class MobileTestBase {

    public static final String deviceHost = System.getProperty("envMobile", "browserstack");

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        switch (deviceHost) {
            case "browserstack" -> Configuration.browser = BrowserstackDriver.class.getName();
            case "emulation" -> Configuration.browser = EmulatorMobileDriver.class.getName();
            case "real" -> Configuration.browser = RealMobileDriver.class.getName();
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();

        Attach.pageSource();
        closeWebDriver();
        if (System.getProperty("deviceHost", "browserstack").equals("browserstack")) {
            Attach.addMobileVideo(sessionId);
        }

    }
}
