package org.itmo.spacemarine.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Chapter
 */
@Validated


public class Chapter   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("world")
  private String world = null;

  public Chapter name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
      @NotNull

  @Size(min=1)   public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Chapter world(String world) {
    this.world = world;
    return this;
  }

  /**
   * Get world
   * @return world
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getWorld() {
    return world;
  }

  public void setWorld(String world) {
    this.world = world;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Chapter chapter = (Chapter) o;
    return Objects.equals(this.name, chapter.name) &&
        Objects.equals(this.world, chapter.world);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, world);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Chapter {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    world: ").append(toIndentedString(world)).append("\n");
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
