package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ApiConfig;
import config.AuthConfig;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void beforeAll() {

        ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class);
        RestAssured.baseURI = apiConfig.baseURI();
        RestAssured.basePath = apiConfig.basePath();

        String browserHost = System.getProperty("browserHost", "remote");
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);

        Configuration.browserSize = webConfig.browserSize();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        if (browserHost.equals("remote")) {
            Configuration.remote = authConfig.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));

            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.addVideo();
        closeWebDriver();
    }
}
