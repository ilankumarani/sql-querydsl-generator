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

### Schema to be generated

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

