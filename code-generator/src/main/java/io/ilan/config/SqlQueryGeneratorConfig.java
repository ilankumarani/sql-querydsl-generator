package io.ilan.config;

import com.querydsl.sql.codegen.MetaDataExporter;
import com.querydsl.sql.codegen.MetadataExporterConfigImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@DependsOn("dbConfig")
@Configuration
@Slf4j
public class SqlQueryGeneratorConfig {


    @Value("${sql.queryDsl.generate.directory:}")
    private String generateRootDirectory;


    @Value("${sql.queryDsl.package.directory:}")
    private String packageDirectory;



    @Bean
    public CommandLineRunner sqlQueryDslGenerator(DataSource dataSource) {
        return args -> {
            java.sql.Connection conn = dataSource.getConnection();
            MetadataExporterConfigImpl metadataExporterConfig = getMetadataExporterConfig();
            //MetaDataExporter exporter = new MetaDataExporter(metadataExporterConfig);
            CustomMetadataExporter exporter = new CustomMetadataExporter(metadataExporterConfig);
            exporter.export(conn.getMetaData());
        };
    }

    /**
     * MetaData builder for Sql query
     * @return MetadataExporterConfig
     */
    private MetadataExporterConfigImpl getMetadataExporterConfig() {
        MetadataExporterConfigImpl metadataExporterConfig = new MetadataExporterConfigImpl();
        metadataExporterConfig.setPackageName(packageDirectory);
        metadataExporterConfig.setNamePrefix("S");
        metadataExporterConfig.setExportAll(Boolean.FALSE);
        metadataExporterConfig.setExportTables(Boolean.TRUE);
        //metadataExporterConfig.setSchemaPattern("blog_schema, Test");
        metadataExporterConfig.setSchemaToPackage(Boolean.TRUE);
        metadataExporterConfig.setTargetFolder(new File(getSrcMainPath().toUri()));
        return metadataExporterConfig;
    }

    /**
     * Getting root directory of the Project
     * @return
     */
    @SneakyThrows
    public Path getSrcMainPath() {
        URL resourceUrl = this.getClass().getResource("");
        Path resourcePath = Paths.get(resourceUrl.toURI());
        Path absolutePath = resourcePath.toAbsolutePath();
        String pathWithOutTarget = absolutePath.toString();

        if (pathWithOutTarget.contains("target")) {
            pathWithOutTarget = pathWithOutTarget.substring(0, pathWithOutTarget.indexOf("target") + 6);
        }

        Path srcMain = Paths.get(pathWithOutTarget.toString(), generateRootDirectory);
        log.info("Target Folder path :: {}", srcMain.toString());
        return srcMain;
    }
}
