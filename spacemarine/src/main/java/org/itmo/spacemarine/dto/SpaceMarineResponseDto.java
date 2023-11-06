package org.itmo.spacemarine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.itmo.spacemarine.entity.Chapter;
import org.itmo.spacemarine.entity.Coordinates;
import org.itmo.spacemarine.entity.Weapon;
import org.itmo.spacemarine.model.AstartesCategory;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMarineResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Coordinates coordinates;

    @NotNull
    @Min(0)
    private Long health;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private String achievements;

    @NotNull
    private AstartesCategory category;

    @NotNull
    private Weapon weaponType;

    @NotNull
    private Chapter chapter;

}
