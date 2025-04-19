package io.ilan.customExport;

import com.querydsl.sql.codegen.MetadataExporterConfig;
import com.querydsl.sql.codegen.MetadataExporterConfigImpl;
import lombok.Data;

import java.util.List;

/**
 * Inclusive the schema and table name
 */
@Data
public class CustomMetadataExporterConfigImpl extends MetadataExporterConfigImpl implements MetadataExporterConfig {

    private List<String> catalogsIncluded;
    private List<String> schemasIncluded;
    private List<String> tablesIncluded;
    private List<String> catalogsExcluded;
    private List<String> schemasExcluded;
    private List<String> tablesExcluded;
}
