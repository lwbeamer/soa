package org.itmo.spacemarine.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets AstartesCategory
 */
public enum AstartesCategory {
  SCOUT("SCOUT"),
    AGGRESSOR("AGGRESSOR"),
    TACTICAL("TACTICAL"),
    LIBRARIAN("LIBRARIAN"),
    APOTHECARY("APOTHECARY");

  private String value;

  AstartesCategory(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AstartesCategory fromValue(String text) {
    for (AstartesCategory b : AstartesCategory.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
