package io.ilan.util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Filter the generation to schema and table
 */
public final class FilterSqlGeneratorUtil {

    /**
     * Default constructor
     */
    private FilterSqlGeneratorUtil() {
    }

    /**
     * Logic to Filter the generation to schema and table
     * @param schemaNames schemaNames
     * @param tableNames tableNames
     * @param schemaName schemaName
     * @param tableName tableName
     * @return the boolean if that needs to be generated or not
     */
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

    /**
     * Lower case the list
     * @param input list as in input
     * @return lower case list or empty List
     */
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
