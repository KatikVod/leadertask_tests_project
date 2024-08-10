package config;

import org.aeonbits.owner.Config;

public interface ApiConfig extends Config {
    @Config.Key("baseURI")
    @Config.DefaultValue("https://web.leadertask.com")
    String baseURI();

    @Config.Key("basePath")
    @Config.DefaultValue("/api/v1")
    String basePath();

}
