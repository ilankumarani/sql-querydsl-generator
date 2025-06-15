package xio.ilan.sql.query.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;


import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * SDummyStudent is a Querydsl query type for BDummyStudent
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SDummyStudent extends com.querydsl.sql.RelationalPathBase<BStudent> {

    private static final long serialVersionUID = -645267012;

    public static final SDummyStudent dummyStudent = new SDummyStudent("DUMMY_STUDENT");

    public final DateTimePath<java.sql.Timestamp> blogDate = createDateTime("blogDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> blogInstant = createDateTime("blogInstant", java.sql.Timestamp.class);

    public final DatePath<java.sql.Date> blogLocalDate = createDate("blogLocalDate", java.sql.Date.class);

    public final DateTimePath<java.sql.Timestamp> blogLocalDateTime = createDateTime("blogLocalDateTime", java.sql.Timestamp.class);

    public final TimePath<java.sql.Time> blogLocalTime = createTime("blogLocalTime", java.sql.Time.class);

    public final DateTimePath<java.sql.Timestamp> blogOffsetDateTime = createDateTime("blogOffsetDateTime", java.sql.Timestamp.class);

    public final DatePath<java.sql.Date> blogSqlDate = createDate("blogSqlDate", java.sql.Date.class);

    public final TimePath<java.sql.Time> blogSqlTime = createTime("blogSqlTime", java.sql.Time.class);

    public final DateTimePath<java.sql.Timestamp> blogSqlTimestamp = createDateTime("blogSqlTimestamp", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> blogZonedDateTime = createDateTime("blogZonedDateTime", java.sql.Timestamp.class);

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final com.querydsl.sql.PrimaryKey<BStudent> constraintF = createPrimaryKey(id);

    public SDummyStudent(String variable) {
        super(BStudent.class, forVariable(variable), "DUMMY_SCHEMA", "DUMMY_STUDENT");
        addMetadata();
    }

    public SDummyStudent(String variable, String schema, String table) {
        super(BStudent.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SDummyStudent(String variable, String schema) {
        super(BStudent.class, forVariable(variable), schema, "DUMMY_STUDENT");
        addMetadata();
    }

    public SDummyStudent(Path<? extends BStudent> path) {
        super(path.getType(), path.getMetadata(), "DUMMY_SCHEMA", "DUMMY_STUDENT");
        addMetadata();
    }

    public SDummyStudent(PathMetadata metadata) {
        super(BStudent.class, metadata, "DUMMY_SCHEMA", "DUMMY_STUDENT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(blogDate, ColumnMetadata.named("BLOG_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogInstant, ColumnMetadata.named("BLOG_INSTANT").withIndex(3).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(blogLocalDate, ColumnMetadata.named("BLOG_LOCAL_DATE").withIndex(4).ofType(Types.DATE).withSize(10));
        addMetadata(blogLocalDateTime, ColumnMetadata.named("BLOG_LOCAL_DATE_TIME").withIndex(5).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogLocalTime, ColumnMetadata.named("BLOG_LOCAL_TIME").withIndex(6).ofType(Types.TIME).withSize(8));
        addMetadata(blogOffsetDateTime, ColumnMetadata.named("BLOG_OFFSET_DATE_TIME").withIndex(7).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogSqlDate, ColumnMetadata.named("BLOG_SQL_DATE").withIndex(8).ofType(Types.DATE).withSize(10));
        addMetadata(blogSqlTime, ColumnMetadata.named("BLOG_SQL_TIME").withIndex(9).ofType(Types.TIME).withSize(8));
        addMetadata(blogSqlTimestamp, ColumnMetadata.named("BLOG_SQL_TIMESTAMP").withIndex(10).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogZonedDateTime, ColumnMetadata.named("BLOG_ZONED_DATE_TIME").withIndex(11).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(category, ColumnMetadata.named("CATEGORY").withIndex(12).ofType(Types.VARCHAR).withSize(255));
        addMetadata(content, ColumnMetadata.named("CONTENT").withIndex(13).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(title, ColumnMetadata.named("TITLE").withIndex(14).ofType(Types.VARCHAR).withSize(255));
    }

}

