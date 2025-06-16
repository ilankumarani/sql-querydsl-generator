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
 * SComments is a Querydsl query type for BComments
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SComments extends com.querydsl.sql.RelationalPathBase<BComments> {

    private static final long serialVersionUID = 1618733937;

    public static final SComments comments = new SComments("COMMENTS");

    public final BooleanPath approved = createBoolean("approved");

    public final StringPath author = createString("author");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> postedat = createDateTime("postedat", java.sql.Timestamp.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath text = createString("text");

    public SComments(String variable) {
        super(BComments.class, forVariable(variable), "PUBLIC", "COMMENTS");
        addMetadata();
    }

    public SComments(String variable, String schema, String table) {
        super(BComments.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SComments(String variable, String schema) {
        super(BComments.class, forVariable(variable), schema, "COMMENTS");
        addMetadata();
    }

    public SComments(Path<? extends BComments> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "COMMENTS");
        addMetadata();
    }

    public SComments(PathMetadata metadata) {
        super(BComments.class, metadata, "PUBLIC", "COMMENTS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(approved, ColumnMetadata.named("APPROVED").withIndex(2).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(author, ColumnMetadata.named("AUTHOR").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(email, ColumnMetadata.named("EMAIL").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(postedat, ColumnMetadata.named("POSTEDAT").withIndex(5).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(postId, ColumnMetadata.named("POST_ID").withIndex(7).ofType(Types.BIGINT).withSize(64));
        addMetadata(text, ColumnMetadata.named("TEXT").withIndex(6).ofType(Types.VARCHAR).withSize(2000));
    }

}

