package org.itmo.spacemarine.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Coordinates
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-12T00:55:51.864730298Z[GMT]")


public class Coordinates   {
  @JsonProperty("x")
  private Double x = null;

  @JsonProperty("y")
  private Double y = null;

  public Coordinates x(Double x) {
    this.x = x;
    return this;
  }

  /**
   * Get x
   * maximum: 872
   * @return x
   **/
  @Schema(description = "")
  @Min(1)
  @Max(10000)   public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Coordinates y(Double y) {
    this.y = y;
    return this;
  }

  /**
   * Get y
   * maximum: 110
   * @return y
   **/
  @Schema(description = "")
  @Min(1)
  @Max(10000)   public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coordinates coordinates = (Coordinates) o;
    return Objects.equals(this.x, coordinates.x) &&
        Objects.equals(this.y, coordinates.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Coordinates {\n");
    
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
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
