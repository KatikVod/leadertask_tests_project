package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorizationBodyModel {
    String login, password, system;

    @JsonProperty("type_device")
    String typeDevice;
}
