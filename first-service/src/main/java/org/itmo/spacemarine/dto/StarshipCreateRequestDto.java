package org.itmo.spacemarine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StarshipCreateRequestDto {

    @NotNull
    private String name;

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;
}
