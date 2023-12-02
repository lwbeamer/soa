package itmo.corp.secondaryservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import itmo.corp.secondaryservice.deserialization.DefaultInstantDeserializer;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private Integer code;
    private String message;

    @JsonDeserialize(using = DefaultInstantDeserializer.class)
    private Instant timestamp;
}
