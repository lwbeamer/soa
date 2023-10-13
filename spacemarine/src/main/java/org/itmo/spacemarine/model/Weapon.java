package org.itmo.spacemarine.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Weapon
 */
public enum Weapon {
  BOLTGUN("BOLTGUN"),
    HEAVY_BOLTGUN("HEAVY_BOLTGUN"),
    PLASMA_GUN("PLASMA_GUN"),
    GRAV_GUN("GRAV_GUN");

  private String value;

  Weapon(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Weapon fromValue(String text) {
    for (Weapon b : Weapon.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
