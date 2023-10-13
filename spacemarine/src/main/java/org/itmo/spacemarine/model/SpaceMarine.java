package org.itmo.spacemarine.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SpaceMarine
 */
@Validated

public class SpaceMarine   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("coordinates")
  private Coordinates coordinates = null;

  @JsonProperty("creationDate")
  private OffsetDateTime creationDate = null;

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

  public SpaceMarine id(Integer id) {
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

  public SpaceMarine name(String name) {
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

  public SpaceMarine coordinates(Coordinates coordinates) {
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

  public SpaceMarine creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public SpaceMarine health(Long health) {
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

  public SpaceMarine achievements(String achievements) {
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

  public SpaceMarine category(AstartesCategory category) {
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

  public SpaceMarine weaponType(Weapon weaponType) {
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

  public SpaceMarine chapter(Chapter chapter) {
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
    SpaceMarine spaceMarine = (SpaceMarine) o;
    return Objects.equals(this.id, spaceMarine.id) &&
        Objects.equals(this.name, spaceMarine.name) &&
        Objects.equals(this.coordinates, spaceMarine.coordinates) &&
        Objects.equals(this.creationDate, spaceMarine.creationDate) &&
        Objects.equals(this.health, spaceMarine.health) &&
        Objects.equals(this.achievements, spaceMarine.achievements) &&
        Objects.equals(this.category, spaceMarine.category) &&
        Objects.equals(this.weaponType, spaceMarine.weaponType) &&
        Objects.equals(this.chapter, spaceMarine.chapter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, coordinates, creationDate, health, achievements, category, weaponType, chapter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpaceMarine {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
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
