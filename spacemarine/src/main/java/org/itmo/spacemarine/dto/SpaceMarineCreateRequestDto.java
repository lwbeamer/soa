package org.itmo.spacemarine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.itmo.spacemarine.entity.Chapter;
import org.itmo.spacemarine.entity.Coordinates;
import org.itmo.spacemarine.entity.Starship;
import org.itmo.spacemarine.entity.Weapon;
import org.itmo.spacemarine.model.AstartesCategory;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMarineCreateRequestDto {

    @NotNull
    private String name;

    @NotNull
    private Coordinates coordinates;

    @NotNull
    @Min(0)
    private Long health;

    @NotNull
    private String achievements;

    @NotNull
    private AstartesCategory category;

    @NotNull
    private Weapon weaponType;

    @NotNull
    private Chapter chapter;
}
