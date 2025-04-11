package io.ilan.config;

import com.querydsl.sql.codegen.MetadataExporterConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Configuration
public class MetadataExporterConfig {

    private static final Logger log = LoggerFactory.getLogger(MetadataExporterConfig.class);

    @Value("${query.dsl.sql.output.directory:#{null}}")
    private String targetOutputDirectory;

    @Value("${query.dsl.sql.package.directory:zolo.query.dsl}")
    private String packageDirectory;

    @Autowired
    ResourceLoader resourceLoader;

    /**
     * MetaData builder for Sql query
     *
     * @return MetadataExporterConfig
     */
    public MetadataExporterConfigImpl getMetadataExporterConfig() {
        MetadataExporterConfigImpl metadataExporterConfig = new MetadataExporterConfigImpl();
        metadataExporterConfig.setPackageName(packageDirectory);
        metadataExporterConfig.setNamePrefix("S");
        metadataExporterConfig.setExportAll(Boolean.FALSE);
        metadataExporterConfig.setExportTables(Boolean.TRUE);
        metadataExporterConfig.setSchemaToPackage(Boolean.TRUE);


        Path path = null;
        if (Objects.nonNull(targetOutputDirectory)) {
            path = Paths.get(targetOutputDirectory);
        } else {
            path = getTargetPath();
        }
        metadataExporterConfig.setTargetFolder(new File(path.toUri()));
        log.info("Target OutputDirectory to be generated :: {}", path.toUri().toString());

        return metadataExporterConfig;
    }

    /**
     * Get target path generated-test-source, this method is just for test case
     *
     * @return
     */
    public Path getTargetPath() {
        Path resourcePath = null;
        try {
            URL resourceUrl = resourceLoader.getResource("").getURL();
            resourcePath = Paths.get(resourceUrl.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Hey Ilan getting error while getting resource URL");
        }
        Path absolutePath = resourcePath.toAbsolutePath();
        String targetPath = absolutePath.toString();

        if (targetPath.contains("target")) {
            targetPath = targetPath.substring(0, targetPath.indexOf("target") + 6);
        }
        Path srcMain = Paths.get(targetPath.toString());
        return srcMain.resolve("generated-sources");
    }
}
