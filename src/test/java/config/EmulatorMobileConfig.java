package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:emulator.properties"
})
public interface EmulatorMobileConfig extends Config {

    String platformVersion();

    String deviceName();

    String appPackage();

    String appActivity();

    String appiumUrl();

    String appLanguage();

    String appLocale();

    String appPath();

}
