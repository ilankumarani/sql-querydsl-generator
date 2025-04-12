package io.ilan.service;

import io.ilan.config.MetadataExporterBuilder;
import io.ilan.customExport.CustomMetadataExporter;
import io.ilan.customExport.CustomMetadataExporterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;


@Service
public class SqlExporterService {

    private static final Logger log = LoggerFactory.getLogger(SqlExporterService.class);
    public static final String pathSpliter = "\\";

    @Autowired
    private MetadataExporterBuilder metadataExporterConfig;

    public void exporter(Connection connection) throws SQLException {
        CustomMetadataExporterConfig var = metadataExporterConfig.getMetadataExporterConfig();
        CustomMetadataExporter exporter = new CustomMetadataExporter(var);
        exporter.export(connection.getMetaData());
        log.info("########### GENERATION COMPLETED on Folder {} ###########", var.getTargetFolder().getPath() + pathSpliter + String.join(pathSpliter, var.getPackageName().split("\\.")));
    }
}
