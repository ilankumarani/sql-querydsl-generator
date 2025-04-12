package io.ilan.service;

import io.ilan.config.MetaDataConfigProperties;
import io.ilan.customExport.CustomMetadataExporterConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RequiredArgsConstructor
@Configuration
public class MetadataExporterBuilder {

    private static final Logger log = LoggerFactory.getLogger(MetadataExporterBuilder.class);

    private final TargetPathBuilder targetPathBuilder;

    private final MetaDataConfigProperties metaDataConfigProperties;

    /**
     * MetaData builder for Sql query
     *
     * @return MetadataExporterConfig
     */
    public CustomMetadataExporterConfig getMetadataExporterConfig() {
        CustomMetadataExporterConfig metadataExporterConfig = new CustomMetadataExporterConfig();
        metadataExporterConfig.setPackageName(metaDataConfigProperties.getPackageDirectory());
        metadataExporterConfig.setNamePrefix("S");
        metadataExporterConfig.setExportAll(Boolean.FALSE);
        metadataExporterConfig.setExportTables(Boolean.TRUE);
        metadataExporterConfig.setSchemaToPackage(Boolean.TRUE);

        setSchemasIncludes(metadataExporterConfig);
        setTablesIncludes(metadataExporterConfig);

        Path path = getGenratedPath();
        log.debug("Target OutputDirectory to be generated :: {}", path.toUri());
        metadataExporterConfig.setTargetFolder(new File(path.toUri()));
        return metadataExporterConfig;
    }

    private void setSchemasIncludes(CustomMetadataExporterConfig metadataExporterConfig) {
        if (Objects.nonNull(metaDataConfigProperties.getSchemas())) {
            metadataExporterConfig.setSchemasIncluded(metaDataConfigProperties.getSchemas());
        }
    }

    private void setTablesIncludes(CustomMetadataExporterConfig metadataExporterConfig) {
        if (Objects.nonNull(metaDataConfigProperties.getTables())) {
            metadataExporterConfig.setTablesIncluded(metaDataConfigProperties.getTables());
        }
    }

    private Path getGenratedPath() {
        Path path = null;
        if (Objects.nonNull(metaDataConfigProperties.getTargetOutputDirectory())) {
            path = Paths.get(metaDataConfigProperties.getTargetOutputDirectory());
        } else {
            path = targetPathBuilder.getTargetPath();
        }
        return path;
    }

}
