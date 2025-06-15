
# How to use SQLQueryFactory with your QueryDSL JPA Entities

Even you want QueryDSL SQL features such as bulk insert, you are using queryDSL JPA, unfortunately there is no way to generate  ```RelationalPath<T>``` from ```EntityPath<T>``` automatically.


[How to generate RelationalPath Derived class using QueryDsl Maven plugin?](https://stackoverflow.com/questions/33608023/how-to-generate-relationalpath-derived-class-using-querydsl-maven-plugin/53675800#53675800)
```text
As @timo Westkämper feedback You can't turn it into a RelationalPath automatically

but you can create RelationalPathBase using your entityPath
```

```java
RelationalPathBase relationalPathBase= new RelationalPathBase(this.entityPath.getType(), this.entityPath.getMetadata(), "yourSchemaName","yourTableName");
```

[Full answer in StackOverflow](https://stackoverflow.com/questions/33608023/how-to-generate-relationalpath-derived-class-using-querydsl-maven-plugin/53675800#53675800)

But there's a good news. you can wrap **```RelationalPath<T>```** from **```EntityPath<T>```** manually like this. so, I made util class that wrap RelationalPath<T> from EntityPath<T>.


#### copy the class below, apply it, and use it.
```java
import com.querydsl.core.types.EntityPath;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.RelationalPathBase;

import javax.persistence.Table;
import java.lang.reflect.AnnotatedElement;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class QueryDslUtils {

    /**
     * Simple Type cache (If you want another cache strategy, make it your own.)
     */
    private static final ConcurrentMap<EntityPath<?>, RelationalPath<?>> relationalMap = new ConcurrentHashMap<>();

    /**
     * Entity Class to SQLQueryFactory RelationalPath
     * @param entityPath
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> RelationalPath<T> asRelational(EntityPath<T> entityPath) {
        AnnotatedElement annotatedElement = Objects.requireNonNull(Objects.requireNonNull(entityPath, "entityPath is null").getAnnotatedElement(), "no annotation");
        Table table = Objects.requireNonNull(annotatedElement.getAnnotation(Table.class), "no entity table");
        RelationalPath<?> result = relationalMap.get(entityPath);
        if(result == null)
            relationalMap.put(entityPath, result = new RelationalPathBase<T>(entityPath.getType(), entityPath.getMetadata(), table.schema(), table.name()));
        return (RelationalPath<T>) result;
    }
}

```

#### How to use? 
that's very simple. </br>
when you are using SQLQueryFactory, then just call QueryDslUtils.asRelational to wrap RelationalPath entity and use any SQLQueryFactory's clause.

![Code Snipet](./images/how_to_use.png)

### [Click for Sample Code 1](../code-generator/src/test/java/xio/ilan/test/BulkQueryDslSqlTest.java)
### [Click for Sample Code 2](../code-generator/src/test/java/xio/ilan/test/CustomQueryDslSqlTest.java)