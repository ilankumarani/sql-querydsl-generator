package io.ilan.service;

import com.querydsl.sql.codegen.MetadataExporterConfigImpl;
import io.ilan.config.MetadataExporterConfig;
import io.ilan.util.CustomMetadataExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;


@Service
public class SqlExporterService {

    private static final Logger log = LoggerFactory.getLogger(SqlExporterService.class);

    @Autowired
    private MetadataExporterConfig metadataExporterConfig;

    public void exporter(Connection connection) throws SQLException {
        MetadataExporterConfigImpl var = metadataExporterConfig.getMetadataExporterConfig();
        CustomMetadataExporter exporter = new CustomMetadataExporter(var);
        exporter.export(connection.getMetaData());
        log.info("########### GENERATION COMPLETED ###########");
    }
}
