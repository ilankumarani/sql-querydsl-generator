package io.ilan.service;

import io.ilan.config.MetaDataConfigProperties;
import io.ilan.customExport.CustomMetadataExporterConfigImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * MetadataExporterBuilder configuration builder
 */
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
    public CustomMetadataExporterConfigImpl getMetadataExporterConfig() {
        CustomMetadataExporterConfigImpl metadataExporterConfig = new CustomMetadataExporterConfigImpl();
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

    private void setSchemasIncludes(CustomMetadataExporterConfigImpl metadataExporterConfig) {
        if (Objects.nonNull(metaDataConfigProperties.getSchemas())) {
            metadataExporterConfig.setSchemasIncluded(metaDataConfigProperties.getSchemas());
        }
    }

    private void setTablesIncludes(CustomMetadataExporterConfigImpl metadataExporterConfig) {
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
