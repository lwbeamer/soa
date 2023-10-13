package org.itmo.spacemarine.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SpaceMarineCreateRequest
 */
@Validated

public class SpaceMarineCreateRequest   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("coordinates")
  private Coordinates coordinates = null;

  @JsonProperty("health")
  private Long health = null;

  @JsonProperty("achievements")
  private String achievements = null;

  @JsonProperty("category")
  private AstartesCategory category = null;

  @JsonProperty("weaponType")
  private Weapon weaponType = null;

  @JsonProperty("chapter")
  private Chapter chapter = null;

  public SpaceMarineCreateRequest id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * minimum: 1
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

  @Min(1)  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SpaceMarineCreateRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SpaceMarineCreateRequest coordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  /**
   * Get coordinates
   * @return coordinates
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public SpaceMarineCreateRequest health(Long health) {
    this.health = health;
    return this;
  }

  /**
   * Get health
   * minimum: 1
   * @return health
   **/
  @Schema(description = "")
  
  @Min(1L)  public Long getHealth() {
    return health;
  }

  public void setHealth(Long health) {
    this.health = health;
  }

  public SpaceMarineCreateRequest achievements(String achievements) {
    this.achievements = achievements;
    return this;
  }

  /**
   * Get achievements
   * @return achievements
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getAchievements() {
    return achievements;
  }

  public void setAchievements(String achievements) {
    this.achievements = achievements;
  }

  public SpaceMarineCreateRequest category(AstartesCategory category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public AstartesCategory getCategory() {
    return category;
  }

  public void setCategory(AstartesCategory category) {
    this.category = category;
  }

  public SpaceMarineCreateRequest weaponType(Weapon weaponType) {
    this.weaponType = weaponType;
    return this;
  }

  /**
   * Get weaponType
   * @return weaponType
   **/
  @Schema(description = "")
  
    @Valid
    public Weapon getWeaponType() {
    return weaponType;
  }

  public void setWeaponType(Weapon weaponType) {
    this.weaponType = weaponType;
  }

  public SpaceMarineCreateRequest chapter(Chapter chapter) {
    this.chapter = chapter;
    return this;
  }

  /**
   * Get chapter
   * @return chapter
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Chapter getChapter() {
    return chapter;
  }

  public void setChapter(Chapter chapter) {
    this.chapter = chapter;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpaceMarineCreateRequest spaceMarineCreateRequest = (SpaceMarineCreateRequest) o;
    return Objects.equals(this.id, spaceMarineCreateRequest.id) &&
        Objects.equals(this.name, spaceMarineCreateRequest.name) &&
        Objects.equals(this.coordinates, spaceMarineCreateRequest.coordinates) &&
        Objects.equals(this.health, spaceMarineCreateRequest.health) &&
        Objects.equals(this.achievements, spaceMarineCreateRequest.achievements) &&
        Objects.equals(this.category, spaceMarineCreateRequest.category) &&
        Objects.equals(this.weaponType, spaceMarineCreateRequest.weaponType) &&
        Objects.equals(this.chapter, spaceMarineCreateRequest.chapter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, coordinates, health, achievements, category, weaponType, chapter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpaceMarineCreateRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
    sb.append("    health: ").append(toIndentedString(health)).append("\n");
    sb.append("    achievements: ").append(toIndentedString(achievements)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    weaponType: ").append(toIndentedString(weaponType)).append("\n");
    sb.append("    chapter: ").append(toIndentedString(chapter)).append("\n");
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
