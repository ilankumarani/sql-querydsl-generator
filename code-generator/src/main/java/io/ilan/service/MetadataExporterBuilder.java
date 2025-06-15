package io.ilan.service;

import io.ilan.config.CustomNamingStrategy;
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
public class MetadataExporterBuilder extends AbstractMetadataExportBuilder{

    private static final Logger log = LoggerFactory.getLogger(MetadataExporterBuilder.class);

    private final TargetPathBuilder targetPathBuilder;

    private final MetaDataConfigProperties metaDataConfigProperties;

    /**
     * MetaData builder for Sql query
     *
     * @return MetadataExporterConfig
     */
    public CustomMetadataExporterConfigImpl getMetadataExporterConfig() {
        Path path = getGenratedPath(metaDataConfigProperties, targetPathBuilder);

        CustomMetadataExporterConfigImpl exporter = new CustomMetadataExporterConfigImpl();
        exporter.setNamingStrategyClass(CustomNamingStrategy.class);
        exporter.setPackageName(metaDataConfigProperties.getPackageDirectory());
        exporter.setSchemasIncluded(metaDataConfigProperties.getInclusive().getSchemas());
        exporter.setTablesIncluded(metaDataConfigProperties.getInclusive().getTables());

        optionalExporterFlags(metaDataConfigProperties, exporter);

        exporter.setCustomTypes(metaDataConfigProperties.getCustomTypes().getCustomType());
        exporter.setTypeMappings(metaDataConfigProperties.getTypeMappings().getTypeMapping());
        exporter.setNumericMappings(metaDataConfigProperties.getNumericMappings().getNumericMapping());
        exporter.setRenameMappings(metaDataConfigProperties.getRenameMappings().getRenameMapping());

        //The below configuration is for B (Projection Bean) Generation
        exporter.setExportBeans(Boolean.TRUE);
        exporter.setBeanAddFullConstructor(Boolean.TRUE);
        exporter.setBeanAddToString(Boolean.TRUE);
        exporter.setBeanPrefix(metaDataConfigProperties.getBeanClassPrefix());
        exporter.setBeanPrintSupertype(Boolean.TRUE);

        //The below configuration is for S Generation
        exporter.setNamePrefix(metaDataConfigProperties.getQueryClassPrefix());
        exporter.setExportTables(Boolean.TRUE);
        exporter.setSchemaToPackage(Boolean.TRUE);
        exporter.setTargetFolder(new File(path.toUri()));

        log.debug("Target OutputDirectory to be generated :: {}", path.toUri());
        return exporter;
    }

}
