package io.ilan.service;

import com.querydsl.codegen.BeanSerializer;
import com.querydsl.codegen.TypeMappings;
import com.querydsl.sql.codegen.support.CustomType;
import com.querydsl.sql.codegen.support.TypeMapping;
import io.ilan.config.MetaDataConfigProperties;
import io.ilan.customExport.CustomMetadataExporterConfigImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
        Path path = getGenratedPath();

        CustomMetadataExporterConfigImpl exporter = new CustomMetadataExporterConfigImpl();

        exporter.setCustomTypes(metaDataConfigProperties.getCustomTypes().getCustomType());
        exporter.setTypeMappings(metaDataConfigProperties.getTypeMappings().getTypeMapping());
        exporter.setNumericMappings(metaDataConfigProperties.getNumericMappings().getNumericMapping());
        exporter.setRenameMappings(metaDataConfigProperties.getRenameMappings().getRenameMapping());

        setSchemasIncludes(exporter);
        setTablesIncludes(exporter);

        //The below configuration is for S Generation
        exporter.setPackageName(metaDataConfigProperties.getPackageDirectory());
        exporter.setNamePrefix("S");
        exporter.setExportAll(Boolean.FALSE);
        exporter.setExportTables(Boolean.TRUE);
        exporter.setSchemaToPackage(Boolean.TRUE);
        exporter.setTargetFolder(new File(path.toUri()));


        //The below configuration is for B (Projection Bean) Generation
        exporter.setExportBeans(Boolean.TRUE);
        exporter.setBeanAddFullConstructor(Boolean.TRUE);
        exporter.setBeanAddToString(Boolean.TRUE);
        exporter.setBeanPrefix("B");
        exporter.setBeanPrintSupertype(Boolean.TRUE);
        exporter.setBeanPackageName(metaDataConfigProperties.getPackageDirectory().concat("bean"));
        exporter.setBeansTargetFolder(new File(path.toUri()));

        log.debug("Target OutputDirectory to be generated :: {}", path.toUri());
        return exporter;
    }

    private void setSchemasIncludes(CustomMetadataExporterConfigImpl metadataExporterConfig) {
        if (Objects.nonNull(metaDataConfigProperties.getInclusive().getSchemas())) {
            metadataExporterConfig.setSchemasIncluded(metaDataConfigProperties.getInclusive().getSchemas());
        }
    }

    private void setTablesIncludes(CustomMetadataExporterConfigImpl metadataExporterConfig) {
        if (Objects.nonNull(metaDataConfigProperties.getInclusive().getTables())) {
            metadataExporterConfig.setTablesIncluded(metaDataConfigProperties.getInclusive().getTables());
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
