package mobile.drivers;

import com.codeborne.selenide.WebDriverProvider;
import common.config.EmulatorMobileConfig;
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


public class EmulatorMobileDriver implements WebDriverProvider {

    private static final EmulatorMobileConfig config = ConfigFactory.create(EmulatorMobileConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return getEmulateDriver();
    }

    public AndroidDriver getEmulateDriver() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion(config.platformVersion())
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
        EmulatorMobileConfig emulatorMobileConfig = ConfigFactory.create(EmulatorMobileConfig.class);
        File app = new File(emulatorMobileConfig.appPath());
        return app.getAbsolutePath();
    }
}
