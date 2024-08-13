package mobile.drivers;

import com.codeborne.selenide.WebDriverProvider;
import common.config.AuthConfig;
import common.config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {


    private static final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class);
    private static final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", authConfig.browserstackUser());
        caps.setCapability("browserstack.key", authConfig.browserstackKey());

        caps.setCapability("app", config.app());

        caps.setCapability("device", config.deviceName());
        caps.setCapability("os_version", config.platformVersion());

        caps.setCapability("project", config.projectName());
        caps.setCapability("build", config.buildName());
        caps.setCapability("name", config.name());
        caps.setCapability("url", config.browserstackUrl());

        try {
            return new RemoteWebDriver(
                    new URL(config.browserstackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
