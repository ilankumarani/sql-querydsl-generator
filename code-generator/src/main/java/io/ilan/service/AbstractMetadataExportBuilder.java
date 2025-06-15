package io.ilan.service;

import io.ilan.config.MetaDataConfigProperties;
import io.ilan.customExport.CustomMetadataExporterConfigImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AbstractMetadataExportBuilder {

    void optionalExporterFlags(MetaDataConfigProperties metaDataConfigProperties, CustomMetadataExporterConfigImpl exporter) {

        MetaDataConfigProperties.Export export = metaDataConfigProperties.getConfig().getExport();

        if (Objects.nonNull(export.getAll()) && export.getAll() == Boolean.TRUE) {
            exporter.setExportAll(export.getAll());
        } else if (Objects.isNull(export.getAll()) || export.getAll() == Boolean.TRUE) {
            exporter.setExportViews(export.getViews());
            exporter.setExportPrimaryKeys(export.getPrimaryKeys());
            exporter.setExportForeignKeys(export.getForeignKeys());
            exporter.setExportDirectForeignKeys(export.getDirectForeignKeys());
            exporter.setExportInverseForeignKeys(export.getInverseForeignKeys());
        }

        String beanClassSuffix = metaDataConfigProperties.getBeanClassSuffix();
        if (Objects.nonNull(beanClassSuffix)) {
            exporter.setBeanSuffix(beanClassSuffix);
        }

        String queryClassSuffix = metaDataConfigProperties.getQueryClassSuffix();
        if (Objects.nonNull(queryClassSuffix)) {
            exporter.setNameSuffix(queryClassSuffix);
        }
    }

    Path getGenratedPath(MetaDataConfigProperties metaDataConfigProperties, TargetPathBuilder targetPathBuilder) {
        String targetOutputDirectory = metaDataConfigProperties.getTargetOutputDirectory();
        return Objects.nonNull(targetOutputDirectory) ? Paths.get(targetOutputDirectory) : targetPathBuilder.getTargetPath();
    }
}
