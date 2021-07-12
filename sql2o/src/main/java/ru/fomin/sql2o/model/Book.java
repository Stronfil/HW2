package ru.fomin.sql2o.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    Long id;
    String title;
    String description;
    int yearOfRelease;

    public static final Map<String,String> COLUMNS_MAPPING = new HashMap<>();

    static {
        COLUMNS_MAPPING.put("id", "id");
        COLUMNS_MAPPING.put("title", "title");
        COLUMNS_MAPPING.put("description", "description");
        COLUMNS_MAPPING.put("release_year","yearOfRelease");
    }
}
