package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${browserHost}.properties"
})
public interface WebConfig extends Config {

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("100.0")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    @DefaultValue("https://www.leadertask.ru/web")
    String baseUrl();

    @DefaultValue("false")
    boolean isRemote();
}