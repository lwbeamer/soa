package org.itmo.spacemarine.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Starship
 */
@Validated

public class Starship   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("spaceMarine")
  @Valid
  private List<SpaceMarine> spaceMarine = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("width")
  private Integer width = null;

  @JsonProperty("height")
  private Integer height = null;

  public Starship id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * minimum: 1
   * @return id
   **/
  @Schema(description = "")
  
  @Min(1)  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Starship spaceMarine(List<SpaceMarine> spaceMarine) {
    this.spaceMarine = spaceMarine;
    return this;
  }

  public Starship addSpaceMarineItem(SpaceMarine spaceMarineItem) {
    if (this.spaceMarine == null) {
      this.spaceMarine = new ArrayList<SpaceMarine>();
    }
    this.spaceMarine.add(spaceMarineItem);
    return this;
  }

  /**
   * Get spaceMarine
   * @return spaceMarine
   **/
  @Schema(description = "")
      @Valid
    public List<SpaceMarine> getSpaceMarine() {
    return spaceMarine;
  }

  public void setSpaceMarine(List<SpaceMarine> spaceMarine) {
    this.spaceMarine = spaceMarine;
  }

  public Starship name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "RosKosmos", description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Starship width(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get width
   * maximum: 532
   * @return width
   **/
  @Schema(description = "")
  
   @Max(532)   public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Starship height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * maximum: 9987
   * @return height
   **/
  @Schema(description = "")
  
   @Max(9987)   public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Starship starship = (Starship) o;
    return Objects.equals(this.id, starship.id) &&
        Objects.equals(this.spaceMarine, starship.spaceMarine) &&
        Objects.equals(this.name, starship.name) &&
        Objects.equals(this.width, starship.width) &&
        Objects.equals(this.height, starship.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, spaceMarine, name, width, height);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Starship {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    spaceMarine: ").append(toIndentedString(spaceMarine)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
