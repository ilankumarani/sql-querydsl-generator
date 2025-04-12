package io.ilan.util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FilterSqlGeneratorUtil {

    private FilterSqlGeneratorUtil() {
    }

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

        System.out.println(defaultValue);
        return defaultValue;
    }

    public static List<String> valuesToLowerCase(List<String> input) {
        if (Objects.nonNull(input) && !input.isEmpty()) {
            return input.stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
