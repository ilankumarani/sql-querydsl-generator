package io.ilan.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Scan the values that are required from plugin
 */
@Data
@Component
@ConfigurationProperties(prefix = "query.dsl.sql")
public class MetaDataConfigProperties {

    @Value("${query.dsl.sql.output.directory:#{null}}")
    private String targetOutputDirectory;

    @Value("${query.dsl.sql.package.directory:zolo.query.dsl}")
    private String packageDirectory;

    @NestedConfigurationProperty
    private final Inclusive inclusive = new Inclusive();

    @NestedConfigurationProperty
    private final Exclusive exclusive = new Exclusive();

    @Data
    public class Inclusive{
        private List<String> schemas;

        private List<String> tables;
    }

    @Data
    public class Exclusive{
        private List<String> schemas;

        private List<String> tables;
    }
}

