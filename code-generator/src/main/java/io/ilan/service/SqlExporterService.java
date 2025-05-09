package io.ilan.service;

import io.ilan.customExport.CustomMetadataExporter;
import io.ilan.customExport.CustomMetadataExporterConfigImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Exporter the sql generator service
 */
@Service
@RequiredArgsConstructor
public class SqlExporterService {

    private static final Logger log = LoggerFactory.getLogger(SqlExporterService.class);
    /**
     * Path splitter
     */
    public static final String pathSplitter = "\\";
    private final MetadataExporterBuilder metadataExporterConfig;

    /**
     * Sql code generator Exporter
     *
     * @param connection connection to DB which it has to be generated
     * @throws SQLException SqlExecption if Connection no found
     */
    public void exporter(Connection connection) throws SQLException {
        CustomMetadataExporterConfigImpl var = metadataExporterConfig.getMetadataExporterConfig();
        CustomMetadataExporter exporter = new CustomMetadataExporter(var);
        exporter.export(connection.getMetaData());
        log.info("########### GENERATION COMPLETED ###########");
        String path = var.getTargetFolder().getPath() + pathSplitter + String.join(pathSplitter, var.getPackageName().split("\\."));
        log.info("Generated in directory :: {}", Paths.get(path).toUri());
    }
}
