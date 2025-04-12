### Output directory code has to generated

> **_NOTE:_**  Default directory is **target** folder

```yaml
query:
  dsl:
    sql:
      output:
        directory:
```


### Package directory code has to generated

> **_NOTE:_**  Default package is **zolo.query.dsl**
```yaml
query:
  dsl:
    sql:
      package:
        directory: alpha.querydsl.sql
```

### Schemas to be generated

> **_NOTE:_**  Default will be generated to all the **schemas**
```yaml
query:
  dsl:
    sql:
      schemas:
        - OWNER_SCHEMA
        - BLOG_SCHEMA
```

### Tables to be generated

> **_NOTE:_**  Default will be generated to all the **Tables**
```yaml
query:
  dsl:
    sql:
      tables:
        - Domains
        - OWNER_DETAILS
```

### To call a service layer in Spring boot
```java
@Bean
public CommandLineRunner sqlQueryDslGenerator(DataSource dataSource, SqlExporterService sqlExporterService) {
    return args -> {
            sqlExporterService.exporter(dataSource.getConnection());
        };
}
```



___

## Self index to re-gain my Knowledge
<details>
  <summary>Click me</summary>

#### 1. CustomMetadataExporter to be copied from MetadataExporter

#### 2. Import Jakarta validation library
```java
   import jakarta.annotation.Nullable;
```
#### 3. Constructor changes in CustomMetadataExporter
```java
private final CustomMetadataExporterConfigImpl config;

public CustomMetadataExporter(CustomMetadataExporterConfigImpl config) {
    this.config = config;
}
```
#### 4. Changes in below method
```java
private void handleTable(DatabaseMetaData md, ResultSet tables) throws SQLException {
    var catalog = tables.getString("TABLE_CAT");
    var schema = tables.getString("TABLE_SCHEM");
    var schemaName = normalize(tables.getString("TABLE_SCHEM"));
    var tableName = normalize(tables.getString("TABLE_NAME"));

    if (FilterSqlGeneratorUtil.codeGenerateFor(FilterSqlGeneratorUtil.valuesToLowerCase(config.getSchemasIncluded()),
            FilterSqlGeneratorUtil.valuesToLowerCase(config.getTablesIncluded()), schemaName, tableName)) {
                /*
                        existing logic of this method to be copied over here
                 */
    }
}
```
</details>