package itmo.corp.secondaryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Starship   {


  private Long id;

  private String name;

  private Integer width;

  private Integer height;

}
