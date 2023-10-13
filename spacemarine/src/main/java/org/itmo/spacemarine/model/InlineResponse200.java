package org.itmo.spacemarine.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200
 */
@Validated

public class InlineResponse200   {
  @JsonProperty("sumHealth")
  private Integer sumHealth = null;

  public InlineResponse200 sumHealth(Integer sumHealth) {
    this.sumHealth = sumHealth;
    return this;
  }

  /**
   * Get sumHealth
   * @return sumHealth
   **/
  @Schema(description = "")
  
    public Integer getSumHealth() {
    return sumHealth;
  }

  public void setSumHealth(Integer sumHealth) {
    this.sumHealth = sumHealth;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200 inlineResponse200 = (InlineResponse200) o;
    return Objects.equals(this.sumHealth, inlineResponse200.sumHealth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sumHealth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    sumHealth: ").append(toIndentedString(sumHealth)).append("\n");
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
