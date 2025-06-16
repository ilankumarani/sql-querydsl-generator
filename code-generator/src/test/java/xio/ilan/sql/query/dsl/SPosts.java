package xio.ilan.sql.query.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * SPosts is a Querydsl query type for BPosts
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SPosts extends com.querydsl.sql.RelationalPathBase<BPosts> {

    private static final long serialVersionUID = 277915510;

    public static final SPosts posts = new SPosts("POSTS");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> createdat = createDateTime("createdat", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath published = createBoolean("published");

    public final StringPath title = createString("title");

    public final DateTimePath<java.sql.Timestamp> updatedat = createDateTime("updatedat", java.sql.Timestamp.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public SPosts(String variable) {
        super(BPosts.class, forVariable(variable), "PUBLIC", "POSTS");
        addMetadata();
    }

    public SPosts(String variable, String schema, String table) {
        super(BPosts.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SPosts(String variable, String schema) {
        super(BPosts.class, forVariable(variable), schema, "POSTS");
        addMetadata();
    }

    public SPosts(Path<? extends BPosts> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "POSTS");
        addMetadata();
    }

    public SPosts(PathMetadata metadata) {
        super(BPosts.class, metadata, "PUBLIC", "POSTS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(category, ColumnMetadata.named("CATEGORY").withIndex(2).ofType(Types.VARCHAR).withSize(255));
        addMetadata(content, ColumnMetadata.named("CONTENT").withIndex(3).ofType(Types.VARCHAR).withSize(5000));
        addMetadata(createdat, ColumnMetadata.named("CREATEDAT").withIndex(4).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(published, ColumnMetadata.named("PUBLISHED").withIndex(5).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(title, ColumnMetadata.named("TITLE").withIndex(6).ofType(Types.VARCHAR).withSize(255));
        addMetadata(updatedat, ColumnMetadata.named("UPDATEDAT").withIndex(7).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(userId, ColumnMetadata.named("USER_ID").withIndex(8).ofType(Types.BIGINT).withSize(64));
    }

}

