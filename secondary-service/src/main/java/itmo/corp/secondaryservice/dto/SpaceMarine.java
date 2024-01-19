package itmo.corp.secondaryservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMarine implements Serializable {
    private Long id;

    private String name;

    private Coordinates coordinates;

//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    private LocalDate creationDate;

    private Long health;

    private String achievements;

    private AstartesCategory category;

    private Weapon weaponType;

    private Chapter chapter;

    private Starship starship;
}
