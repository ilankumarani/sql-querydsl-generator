package xio.ilan.sql.query.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * SStudent is a Querydsl query type for BStudent
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SStudent extends com.querydsl.sql.RelationalPathBase<BStudent> {

    private static final long serialVersionUID = 421520281;

    public static final SStudent student = new SStudent("STUDENT");

    public final DateTimePath<java.sql.Timestamp> blogDate = createDateTime("blogDate", java.sql.Timestamp.class);

    public final SimplePath<Object> blogDuration = createSimple("blogDuration", Object.class);

    public final DateTimePath<java.sql.Timestamp> blogInstant = createDateTime("blogInstant", java.sql.Timestamp.class);

    public final DatePath<java.sql.Date> blogLocalDate = createDate("blogLocalDate", java.sql.Date.class);

    public final DateTimePath<java.sql.Timestamp> blogLocalDateTime = createDateTime("blogLocalDateTime", java.sql.Timestamp.class);

    public final TimePath<java.sql.Time> blogLocalTime = createTime("blogLocalTime", java.sql.Time.class);

    public final DateTimePath<java.sql.Timestamp> blogOffsetDateTime = createDateTime("blogOffsetDateTime", java.sql.Timestamp.class);

    public final SimplePath<byte[]> blogPeriod = createSimple("blogPeriod", byte[].class);

    public final DatePath<java.sql.Date> blogSqlDate = createDate("blogSqlDate", java.sql.Date.class);

    public final TimePath<java.sql.Time> blogSqlTime = createTime("blogSqlTime", java.sql.Time.class);

    public final DateTimePath<java.sql.Timestamp> blogSqlTimestamp = createDateTime("blogSqlTimestamp", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> blogZonedDateTime = createDateTime("blogZonedDateTime", java.sql.Timestamp.class);

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final com.querydsl.sql.PrimaryKey<BStudent> constraintB = createPrimaryKey(id);

    public SStudent(String variable) {
        super(BStudent.class, forVariable(variable), "STUDENT_DETAIL_SCHEMA", "STUDENT");
        addMetadata();
    }

    public SStudent(String variable, String schema, String table) {
        super(BStudent.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SStudent(String variable, String schema) {
        super(BStudent.class, forVariable(variable), schema, "STUDENT");
        addMetadata();
    }

    public SStudent(Path<? extends BStudent> path) {
        super(path.getType(), path.getMetadata(), "STUDENT_DETAIL_SCHEMA", "STUDENT");
        addMetadata();
    }

    public SStudent(PathMetadata metadata) {
        super(BStudent.class, metadata, "STUDENT_DETAIL_SCHEMA", "STUDENT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(blogDate, ColumnMetadata.named("BLOG_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogDuration, ColumnMetadata.named("BLOG_DURATION").withIndex(3).ofType(Types.OTHER).withSize(18).withDigits(9));
        addMetadata(blogInstant, ColumnMetadata.named("BLOG_INSTANT").withIndex(4).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(blogLocalDate, ColumnMetadata.named("BLOG_LOCAL_DATE").withIndex(5).ofType(Types.DATE).withSize(10));
        addMetadata(blogLocalDateTime, ColumnMetadata.named("BLOG_LOCAL_DATE_TIME").withIndex(6).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogLocalTime, ColumnMetadata.named("BLOG_LOCAL_TIME").withIndex(7).ofType(Types.TIME).withSize(8));
        addMetadata(blogOffsetDateTime, ColumnMetadata.named("BLOG_OFFSET_DATE_TIME").withIndex(8).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogPeriod, ColumnMetadata.named("BLOG_PERIOD").withIndex(9).ofType(Types.VARBINARY).withSize(255));
        addMetadata(blogSqlDate, ColumnMetadata.named("BLOG_SQL_DATE").withIndex(10).ofType(Types.DATE).withSize(10));
        addMetadata(blogSqlTime, ColumnMetadata.named("BLOG_SQL_TIME").withIndex(11).ofType(Types.TIME).withSize(8));
        addMetadata(blogSqlTimestamp, ColumnMetadata.named("BLOG_SQL_TIMESTAMP").withIndex(12).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(blogZonedDateTime, ColumnMetadata.named("BLOG_ZONED_DATE_TIME").withIndex(13).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(category, ColumnMetadata.named("CATEGORY").withIndex(14).ofType(Types.VARCHAR).withSize(255));
        addMetadata(content, ColumnMetadata.named("CONTENT").withIndex(15).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(title, ColumnMetadata.named("TITLE").withIndex(16).ofType(Types.VARCHAR).withSize(255));
    }

}

