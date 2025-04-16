### To code Generate via plugin

> **_NOTE:_**  
> * Bundle the entities as a JAR, then pass the base package of entities to plugin as in below example.
  </br> --jpa.entities.base-packages=com.ilan.entity,io.ilan.entity
> * You can have the repository layer also part of the JAR, then pass the base package of repositories to plugin as in below example.
  </br> --jpa.repositories.base-packages=com.ilan.repo,io.ilan.repo

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <!-- You can use the latest-->
    <version>3.5.0</version>
    <configuration>
        <mainClass>com.ilan.QueryDslSqlApplication</mainClass>
        <cleanupDaemonThreads>false</cleanupDaemonThreads>
        <includePluginDependencies>true</includePluginDependencies>
        <includeProjectDependencies>true</includeProjectDependencies>
        <additionalClasspathElements>true</additionalClasspathElements>
        <arguments>
            <argument>--jpa.entities.base-packages=com.ilan.entity,io.ilan.entity</argument>
            <argument>--jpa.repositories.base-packages=com.ilan.repo,io.ilan.repo</argument>
            <argument>--query.dsl.sql.output.directory=${project.basedir}/target/generated-sources</argument>
            <argument>--query.dsl.sql.package.directory=alpha.querydsl.sql</argument>
            <argument>--query.dsl.sql.inclusive.schemas=information_schema,OWNER_SCHEMA,BLOG_SCHEMA</argument>
            <argument>--query.dsl.sql.inclusive.tables=Domains,OWNER_DETAILS</argument>
        </arguments>
    </configuration>
    <executions>
        <execution>
            <id>sql-code-generator</id>
            <phase>generate-sources</phase>
            <goals>
                <goal>java</goal>
            </goals>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>io.github.ilankumarani</groupId>
            <artifactId>code-generator-test</artifactId>
            <!-- At this point in time 0.3.0 is the latest version-->
            <version>0.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <!-- version of your Spring project-->
            <version>3.4.2</version>
        </dependency>
    </dependencies>
</plugin>
```

### To code generate via Spring boot add the below dependency

```xml
<dependency>
    <groupId>io.github.ilankumarani</groupId>
    <artifactId>code-generator-test</artifactId>
    <!-- At this point in time 0.3.0 is the latest version-->
    <version>0.3.0</version>
</dependency>
```
> **_NOTE:_**  To disable generation by property

```yaml
query:
  dsl:
    sql:
      generation:
        enabled: false
```

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
      inclusive:
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
      inclusive:
        tables:
          - Domains
          - OWNER_DETAILS
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