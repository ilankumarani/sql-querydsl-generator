package io.ilan.config;

import com.querydsl.sql.codegen.support.CustomType;
import com.querydsl.sql.codegen.support.NumericMapping;
import com.querydsl.sql.codegen.support.RenameMapping;
import com.querydsl.sql.codegen.support.TypeMapping;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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


    private String queryClassPrefix = "S";
    private String queryClassSuffix = null;

    private String beanClassPrefix = "B";
    private String beanClassSuffix = null;

    @NestedConfigurationProperty
    private final Inclusive inclusive = new Inclusive();

    @NestedConfigurationProperty
    private final Exclusive exclusive = new Exclusive();

    @Data
    public class Inclusive {
        private List<String> schemas = new ArrayList<>();

        private List<String> tables = new ArrayList<>();
    }

    @Data
    public class Exclusive {
        private List<String> schemas = new ArrayList<>();

        private List<String> tables = new ArrayList<>();
    }


    @NestedConfigurationProperty
    private final TypeMappings typeMappings = new TypeMappings();

    @NestedConfigurationProperty
    private final NumericMappings numericMappings = new NumericMappings();

    @NestedConfigurationProperty
    private final RenameMappings renameMappings = new RenameMappings();


    @Data
    public class TypeMappings {
        private List<TypeMapping> typeMapping = new ArrayList<>();
    }

    @Data
    public class NumericMappings {
        private List<NumericMapping> numericMapping = new ArrayList<>();
    }

    @Data
    public class RenameMappings {
        private List<RenameMapping> renameMapping = new ArrayList<>();
    }

    @NestedConfigurationProperty
    private final CustomTypes customTypes = new CustomTypes();

    @Data
    public class CustomTypes {
        private List<CustomType> customType = new ArrayList<>();
    }

}

