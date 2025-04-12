package io.ilan.config;

import io.ilan.customExport.CustomMetadataExporterConfig;
import io.ilan.service.TargetPathBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
public class MetadataExporterBuilder {

    private static final Logger log = LoggerFactory.getLogger(MetadataExporterBuilder.class);

    private final TargetPathBuilder targetPathBuilder;

    private final  MetaDataConfigProperties metaDataConfigProperties;

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

        if (Objects.nonNull(metaDataConfigProperties.getSchemas())) {
            metadataExporterConfig.setSchemasIncluded(metaDataConfigProperties.getSchemas());
        }

        if (Objects.nonNull(metaDataConfigProperties.getTables())) {
            metadataExporterConfig.setTablesIncluded(metaDataConfigProperties.getTables());
        }

        Path path = null;
        if (Objects.nonNull(metaDataConfigProperties.getTargetOutputDirectory())) {
            path = Paths.get(metaDataConfigProperties.getTargetOutputDirectory());
        } else {
            path = targetPathBuilder.getTargetPath();
        }
        metadataExporterConfig.setTargetFolder(new File(path.toUri()));
        log.info("Target OutputDirectory to be generated :: {}", path.toUri().toString());

        return metadataExporterConfig;
    }


}
