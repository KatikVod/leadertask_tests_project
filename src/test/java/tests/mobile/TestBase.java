package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.EmulatorMobileDriver;
import drivers.RealMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    public static String deviceHost = System.getProperty("envMobile", "browserstack");

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        if (deviceHost.equals("browserstack")) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else if (deviceHost.equals("emulation")) {
            Configuration.browser = EmulatorMobileDriver.class.getName();
        } else if (deviceHost.equals("real")) {
            Configuration.browser = RealMobileDriver.class.getName();
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
