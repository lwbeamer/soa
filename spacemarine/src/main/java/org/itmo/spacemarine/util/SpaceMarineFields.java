package org.itmo.spacemarine.util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SpaceMarineFields {
    ID("id", null, "id"),
    NAME("name", null, "name"),
    HEALTH("health", null, "health"),

    ACHIEVEMENTS("achievements", null, "achievements"),

    CATEGORY("category", null, "category"),

    WEAPON_TYPE("weaponType", null, "weaponType"),

    CHAPTER_NAME("chapter", "name", "chapterName"),
    CHAPTER_WORLD("chapter", "world", "chapterWorld"),

    CREATION_DATE("creationDate", null, "creationDate"),
    COORDINATES_X("coordinates", "x", "coordinatesX"),
    COORDINATES_Y("coordinates", "y", "coordinatesY");


    private static final Map<String, SpaceMarineFields> BY_QUERY_PARAM_NAME = new HashMap<>();

    static {
        for (SpaceMarineFields c : values()) {
            BY_QUERY_PARAM_NAME.put(c.queryParamName, c);
        }
        System.out.println("МАПА С ПАОАМАМИ В ЕНАМ " + BY_QUERY_PARAM_NAME);
    }


    private final String queryParamName;
    private final String mainField;
    private final String nestedField;

    SpaceMarineFields(String mainField, String nestedField, String queryParamName) {
        this.mainField = mainField;
        this.nestedField = nestedField;
        this.queryParamName = queryParamName;
    }

    public static SpaceMarineFields valueOfQueryParam(String param) {
        System.out.println("ЗАПРАШИВАЕМ ПАРАМЕТР = "+ param);
        return BY_QUERY_PARAM_NAME.get(param);
    }
}
