package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:browserstack.properties"
})
public interface BrowserstackConfig extends Config {

    String app();

    String deviceName();

    String platformVersion();

    String projectName();

    String buildName();

    String name();

    String browserstackUrl();
}