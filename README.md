### To code Generate via plugin

> **_NOTE:_**  
> * Bundle the entities as a JAR, then pass the base package of entities to plugin as in below example.
  </br> **--jpa.entities.base-packages=com.ilan.entity,io.ilan.entity**
> 
> **Sample Example how to build the Entity in JAR bundle as follows :**
> https://github.com/ilankumarani/sql-querydsl-generator/tree/main/sample-example
> 
> **Sample plugin definitions:**
> https://github.com/ilankumarani/sql-querydsl-generator/blob/main/sample-example/micro-service-sample/pom.xml#L57-L145

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <!-- You can use the latest-->
  <version>3.5.0</version>
  <executions>
    <execution>
      <configuration>
        <mainClass>com.ilan.SqlApplication</mainClass>
        <cleanupDaemonThreads>false</cleanupDaemonThreads>
        <includePluginDependencies>true</includePluginDependencies>
        <includeProjectDependencies>true</includeProjectDependencies>
        <additionalClasspathElements>true</additionalClasspathElements>
        <arguments>
          <!-- Mandatory args we need to provide the packages for entity-scan -->
          <argument>--jpa.entities.base-packages=com.ilan.entity,org.ilan.entity,xio.ilan.entity</argument>
          
          <argument>--query.dsl.sql.output.directory=${project.basedir}/target/generated-sources</argument>
          
          <argument>--query.dsl.sql.package.directory=alpha.querydsl.sql</argument>
          
          <argument>--query.dsl.sql.inclusive.schemas=BLOG_SCHEMA,OWNER_SCHEMA,FORUM_SCHEMA</argument>
          
          <argument>--query.dsl.sql.inclusive.tables=BLOG_DETAILS,OWNER_DETAILS,FORUM_DETAILS</argument>
          
          <argument>--spring.jpa.hibernate.naming.physical-strategy=org.ilan.namingStrategy.CustomPhysicalNamingStrategy</argument>
          
          <argument>--spring.config.additional-location=${project.basedir}/src/main/resources/application-external.yml</argument>
        </arguments>
      </configuration>
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
      <artifactId>code-generator-app</artifactId>
      <!-- At this point in time 1.1.0 is the latest version-->
      <version>${project.version}</version>
    </dependency>
    <!-- I have a naming-strategy in below dependency-->
    <dependency>
      <groupId>io.github.ilankumarani</groupId>
      <artifactId>naming-strategy-resolver</artifactId>
      <version>0.1.2</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
      <!-- version of your Spring project-->
      <version>3.0.0</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
      <!-- version of your Spring project-->
      <version>3.0.0</version>
    </dependency>
  </dependencies>
</plugin>
```

### To code generate via Spring boot add the below dependency

```xml
<dependency>
    <groupId>io.github.ilankumarani</groupId>
    <artifactId>code-generator</artifactId>
    <!-- At this point in time 1.1.0 is the latest version-->
    <version>1.1.0</version>
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

### Prefix & Suffix for Query class and Bean class 

```yaml
query:
  dsl:
    sql:
      query-class-prefix: A
      bean-class-prefix: B
      query-class-suffix: X
      bean-class-suffix: Y
```

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

### Custom property for Exporter

> **_NOTE:_**  **https://querydsl.com/static/querydsl/latest/reference/html/ch02s03.html**
```yaml
query:
  dsl:
    sql:
      output:
        directory: classpath:/
      package:
        directory: schemas.querydsl.sql
      inclusive:
        schemas:
          - OWNER_SCHEMA
          - BLOG_SCHEMA
        tables:
          - Domains
          - OWNER_DETAILS
      query-class-prefix: A
      bean-class-prefix: B
      query-class-suffix: X
      bean-class-suffix: Y
      customTypes:
        customType:
          - com.querydsl.sql.types.InputStreamType
      typeMappings:
        typeMapping:
          - table: BLOG_DETAILS
            column: BLOG_DATE
            type: java.util.Date

          - table: BLOG_DETAILS
            column: BLOG_SQL_DATE
            type: java.sql.Date

          - table: BLOG_DETAILS
            column: BLOG_SQL_TIME
            type: java.sql.Time

          - table: BLOG_DETAILS
            column: BLOG_SQL_TIMESTAMP
            type: java.sql.Timestamp

          - table: BLOG_DETAILS
            column: BLOG_LOCAL_DATE
            type: java.time.LocalDate

          - table: BLOG_DETAILS
            column: BLOG_LOCAL_TIME
            type: java.time.LocalTime

          - table: BLOG_DETAILS
            column: BLOG_LOCAL_DATE_TIME
            type: java.time.LocalDateTime

          - table: BLOG_DETAILS
            column: BLOG_OFFSET_DATE_TIME
            type: java.time.OffsetDateTime

          - table: BLOG_DETAILS
            column: BLOG_ZONED_DATE_TIME
            type: java.time.ZonedDateTime

          - table: BLOG_DETAILS
            column: BLOG_INSTANT
            type: java.time.Instant

      #          - table: BLOG_DETAILS
      #            column: BLOG_DURATION
      #            type: java.time.Duration
      #
      #          - table: BLOG_DETAILS
      #            column: BLOG_PERIOD
      #            type: java.time.Period
      renameMappings:
        renameMapping:
          - fromSchema: source_schema
            fromTable: old_table
            fromColumn: old_column
            toSchema: target_schema
            toTable: new_table
            toColumn: new_column

          - fromSchema: legacy_schema
            fromTable: legacy_users
            fromColumn: uname
            toSchema: modern_schema
            toTable: users
            toColumn: username
      numericMappings:
        numericMapping:
          - total: 1
            decimal: 0
            javaType: java.lang.Byte

          - total: 5
            decimal: 2
            javaType: java.math.BigDecimal
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