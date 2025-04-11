package io.ilan.config;

import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class FilterSqlGeneratorUtil {

    private FilterSqlGeneratorUtil() {
    }

    /*List<String> catalogNames = patternAsList(config.getCatalogPattern());
    List<String> schemaNames = patternAsList(config.getSchemaPattern());
    List<String> tableNames = patternAsList(config.getTableNamePattern());*/

    public static Boolean codeGenerateFor(List<String> schemaNames, List<String> tableNames, String schemaName, String tableName) {

        Boolean defaultValue = Boolean.FALSE;

        if (schemaNames.isEmpty() && tableNames.isEmpty()) {
            defaultValue = Boolean.TRUE;
        } else if (!schemaNames.isEmpty() && !tableNames.isEmpty()) {
            defaultValue = schemaNames.contains(schemaName.toLowerCase()) && tableNames.contains(tableName.toLowerCase());
        } else if (!schemaNames.isEmpty() && tableNames.isEmpty()) {
            defaultValue = schemaNames.contains(schemaName.toLowerCase());
        } else if (schemaNames.isEmpty() && !tableNames.isEmpty()) {
            defaultValue = tableNames.contains(tableName.toLowerCase());
        }

        return defaultValue;
    }

    public static List<String> patternAsList(@Nullable String input) {
        if (input != null && input.contains(",")) {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
