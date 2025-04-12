package io.ilan.customExport;

import com.querydsl.sql.codegen.MetadataExporterConfig;
import com.querydsl.sql.codegen.MetadataExporterConfigImpl;
import lombok.Data;

import java.util.List;

@Data
public class CustomMetadataExporterConfigImpl extends MetadataExporterConfigImpl implements MetadataExporterConfig {

    private List<String> schemasIncluded;
    private List<String> catalogsIncluded;
    private List<String> tablesIncluded;


}
