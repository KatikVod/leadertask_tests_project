package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateUserBodyModel {

    String email, password, phone, name;

    @JsonProperty("type_device")
    String typeDevice = "web";

    String language = "russian";

    String system = "web";
}
