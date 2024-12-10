package io.ilan.config;

import com.querydsl.sql.codegen.MetadataExporterConfigImpl;
import io.ilan.GenerateSqlDslApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@DependsOn("dbConfig")
@Configuration
public class SqlQueryGeneratorConfig {

    private static final Logger log = LoggerFactory.getLogger(SqlQueryGeneratorConfig.class);

    @Value("${target.outputDirectory:}")
    private String targetOutputDirectory;

    @Value("${target.package.directory:query.dsl}")
    private String targetPackageDirectory;

    @Autowired
    private Environment environment;

    @Bean
    public CommandLineRunner sqlQueryDslGenerator(DataSource dataSource) {
        return args -> {
            java.sql.Connection conn = dataSource.getConnection();
            MetadataExporterConfigImpl metadataExporterConfig = getMetadataExporterConfig();
            /*
                Disabled the actual MetaDataExporter because it creates the sqlQuery for Informatic_schema on h2-database
             */
            // MetaDataExporter exporter = new MetaDataExporter(metadataExporterConfig);
            CustomMetadataExporter exporter = new CustomMetadataExporter(metadataExporterConfig);
            exporter.export(conn.getMetaData());
        };
    }

    /**
     * MetaData builder for Sql query
     *
     * @return MetadataExporterConfig
     */
    private MetadataExporterConfigImpl getMetadataExporterConfig() {
        MetadataExporterConfigImpl metadataExporterConfig = new MetadataExporterConfigImpl();
        metadataExporterConfig.setPackageName(targetPackageDirectory);
        metadataExporterConfig.setNamePrefix("S");
        metadataExporterConfig.setExportAll(Boolean.FALSE);
        metadataExporterConfig.setExportTables(Boolean.TRUE);
        metadataExporterConfig.setSchemaToPackage(Boolean.TRUE);

        String profiles[] = environment.getActiveProfiles();
        Arrays.stream(profiles)
                .forEach(profile -> {
                    if (profile.equalsIgnoreCase("package") || profile.equalsIgnoreCase("packages")) {
                        metadataExporterConfig.setTargetFolder(new File(getTargetPath().toUri()));
                    } else {
                        log.info("Target OutputDirectory :: {}", targetOutputDirectory);
                        Path path = Paths.get(targetOutputDirectory);
                        metadataExporterConfig.setTargetFolder(new File(path.toString()));
                    }
                });
        return metadataExporterConfig;
    }

    /**
     * Getting root directory of the Project
     *
     * @return
     */
    public Path getTargetPath() {
        URL resourceUrl = this.getClass().getResource("");
        Path resourcePath = null;
        try {
            resourcePath = Paths.get(resourceUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Path absolutePath = resourcePath.toAbsolutePath();
        String pathWithOutTarget = absolutePath.toString();

        if (pathWithOutTarget.contains("target")) {
            pathWithOutTarget = pathWithOutTarget.substring(0, pathWithOutTarget.indexOf("target") + 6);
        }

        Path srcMain = Paths.get(pathWithOutTarget.toString());
        return srcMain.resolve("generated-test-sources");
    }
}
