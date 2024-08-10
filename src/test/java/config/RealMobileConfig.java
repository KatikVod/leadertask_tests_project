package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:real.properties"
})
public interface RealMobileConfig extends Config {

    String deviceName();

    String appiumUrl();

    String appPackage();

    String appActivity();

    String appLanguage();

    String appLocale();

    String appPath();

}
