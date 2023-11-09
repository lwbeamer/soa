package itmo.corp.secondaryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMarineUpdateDto implements Serializable {

    private String name;

    private Coordinates coordinates;

    private Long health;

    private String achievements;

    private AstartesCategory category;

    private Weapon weaponType;

    private Chapter chapter;

    private Long starshipId;
}
