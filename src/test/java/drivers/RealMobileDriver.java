package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.EmulatorMobileConfig;
import config.RealMobileConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class RealMobileDriver implements WebDriverProvider {

    private static final RealMobileConfig config = ConfigFactory.create(RealMobileConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return getRealDriver();
    }

    public AndroidDriver getRealDriver() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(config.deviceName())
                .setApp(getAppPath())
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity())
                .setLanguage(config.appLanguage())
                .setLocale(config.appLocale());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.appiumUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    private String getAppPath() {
        RealMobileConfig realMobileConfig = ConfigFactory.create(RealMobileConfig.class);
        File app = new File(realMobileConfig.appPath());
        return app.getAbsolutePath();
    }
}
