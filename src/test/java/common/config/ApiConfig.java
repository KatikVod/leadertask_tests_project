package common.config;

import org.aeonbits.owner.Config;

public interface ApiConfig extends Config {
    @Config.DefaultValue("https://web.leadertask.com")
    String baseURI();

    @Config.DefaultValue("/api/v1")
    String basePath();

}
