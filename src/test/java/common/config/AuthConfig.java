package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {
    String email();

    String password();

    String browserstackUser();

    String browserstackKey();

    String remoteUrl();

    String userName();

}
