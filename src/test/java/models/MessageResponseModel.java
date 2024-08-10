package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageResponseModel {

    @JsonProperty("Message")
    String message;
}
