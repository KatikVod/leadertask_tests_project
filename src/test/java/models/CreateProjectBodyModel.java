package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateProjectBodyModel {

    String uid, name;

    @JsonProperty("email_creator")
    String emailCreator;
}

