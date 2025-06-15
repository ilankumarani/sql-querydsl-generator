package io.ilan.util;

import com.querydsl.core.types.EntityPath;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.RelationalPathBase;
import io.ilan.config.SpellResolver;
import jakarta.persistence.Table;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.AnnotatedElement;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//https://dev.to/composite/how-to-use-sqlqueryfactory-with-your-querydsl-jpa-entities-k64
public class QueryDslUtils {

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
        StringValueResolver stringValueResolver = SpellResolver.getResolver();
        AnnotatedElement annotatedElement = Objects.requireNonNull(Objects.requireNonNull(entityPath, "entityPath is null").getAnnotatedElement(), "no annotation");
        Table table = Objects.requireNonNull(annotatedElement.getAnnotation(Table.class), "no entity table");
        RelationalPath<?> result = relationalMap.get(entityPath);
        if(result == null)
            relationalMap.put(entityPath, result = new RelationalPathBase<T>(entityPath.getType(), entityPath.getMetadata(), stringValueResolver.resolveStringValue(table.schema()), stringValueResolver.resolveStringValue(table.name())));
        return (RelationalPath<T>) result;
    }
}
