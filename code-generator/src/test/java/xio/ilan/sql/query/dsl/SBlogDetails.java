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
 * SBlogDetails is a Querydsl query type for BBlogDetails
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SBlogDetails extends com.querydsl.sql.RelationalPathBase<BBlogDetails> {

    private static final long serialVersionUID = 904951595;

    public static final SBlogDetails blogDetails = new SBlogDetails("BLOG_DETAILS");

    public final DateTimePath<java.util.Date> blogDate = createDateTime("blogDate", java.util.Date.class);

    public final SimplePath<Object> blogDuration = createSimple("blogDuration", Object.class);

    public final DateTimePath<java.time.Instant> blogInstant = createDateTime("blogInstant", java.time.Instant.class);

    public final DatePath<java.time.LocalDate> blogLocalDate = createDate("blogLocalDate", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> blogLocalDateTime = createDateTime("blogLocalDateTime", java.time.LocalDateTime.class);

    public final TimePath<java.time.LocalTime> blogLocalTime = createTime("blogLocalTime", java.time.LocalTime.class);

    public final DateTimePath<java.time.OffsetDateTime> blogOffsetDateTime = createDateTime("blogOffsetDateTime", java.time.OffsetDateTime.class);

    public final SimplePath<byte[]> blogPeriod = createSimple("blogPeriod", byte[].class);

    public final DatePath<java.sql.Date> blogSqlDate = createDate("blogSqlDate", java.sql.Date.class);

    public final TimePath<java.sql.Time> blogSqlTime = createTime("blogSqlTime", java.sql.Time.class);

    public final DateTimePath<java.sql.Timestamp> blogSqlTimestamp = createDateTime("blogSqlTimestamp", java.sql.Timestamp.class);

    public final DateTimePath<java.time.ZonedDateTime> blogZonedDateTime = createDateTime("blogZonedDateTime", java.time.ZonedDateTime.class);

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final com.querydsl.sql.PrimaryKey<BBlogDetails> constraintC = createPrimaryKey(id);

    public SBlogDetails(String variable) {
        super(BBlogDetails.class, forVariable(variable), "BLOG_SCHEMA", "BLOG_DETAILS");
        addMetadata();
    }

    public SBlogDetails(String variable, String schema, String table) {
        super(BBlogDetails.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SBlogDetails(String variable, String schema) {
        super(BBlogDetails.class, forVariable(variable), schema, "BLOG_DETAILS");
        addMetadata();
    }

    public SBlogDetails(Path<? extends BBlogDetails> path) {
        super(path.getType(), path.getMetadata(), "BLOG_SCHEMA", "BLOG_DETAILS");
        addMetadata();
    }

    public SBlogDetails(PathMetadata metadata) {
        super(BBlogDetails.class, metadata, "BLOG_SCHEMA", "BLOG_DETAILS");
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

