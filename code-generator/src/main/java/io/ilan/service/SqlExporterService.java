package io.ilan.service;

import io.ilan.customExport.CustomMetadataExporter;
import io.ilan.customExport.CustomMetadataExporterConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;


@Service
@RequiredArgsConstructor
public class SqlExporterService {

    private static final Logger log = LoggerFactory.getLogger(SqlExporterService.class);
    public static final String pathSplitter = "\\";
    private final MetadataExporterBuilder metadataExporterConfig;

    public void exporter(Connection connection) throws SQLException {
        CustomMetadataExporterConfig var = metadataExporterConfig.getMetadataExporterConfig();
        CustomMetadataExporter exporter = new CustomMetadataExporter(var);
        exporter.export(connection.getMetaData());
        log.info("########### GENERATION COMPLETED ###########");
        String path = var.getTargetFolder().getPath() + pathSplitter + String.join(pathSplitter, var.getPackageName().split("\\."));
        log.info("Target OutputDirectory to be generated :: {}", Paths.get(path).toUri() );
    }
}
