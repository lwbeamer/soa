package org.itmo.spacemarine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.itmo.spacemarine.entity.AstartesCategory;
import org.itmo.spacemarine.entity.Chapter;
import org.itmo.spacemarine.entity.Coordinates;
import org.itmo.spacemarine.entity.Weapon;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss'Z'")
    private LocalDateTime creationDate;

    @NotNull
    private String achievements;

    @NotNull
    private AstartesCategory category;

    @NotNull
    private Weapon weaponType;

    @NotNull
    private Chapter chapter;

    @NotNull
    private Long starshipId;
}
