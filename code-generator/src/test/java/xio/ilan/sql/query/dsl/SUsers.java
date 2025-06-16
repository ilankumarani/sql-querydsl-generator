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
 * SUsers is a Querydsl query type for BUsers
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SUsers extends com.querydsl.sql.RelationalPathBase<BUsers> {

    private static final long serialVersionUID = 282638763;

    public static final SUsers users = new SUsers("USERS");

    public final BooleanPath active = createBoolean("active");

    public final StringPath email = createString("email");

    public final StringPath fullname = createString("fullname");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final DateTimePath<java.sql.Timestamp> registeredat = createDateTime("registeredat", java.sql.Timestamp.class);

    public final StringPath username = createString("username");

    public SUsers(String variable) {
        super(BUsers.class, forVariable(variable), "PUBLIC", "USERS");
        addMetadata();
    }

    public SUsers(String variable, String schema, String table) {
        super(BUsers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SUsers(String variable, String schema) {
        super(BUsers.class, forVariable(variable), schema, "USERS");
        addMetadata();
    }

    public SUsers(Path<? extends BUsers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "USERS");
        addMetadata();
    }

    public SUsers(PathMetadata metadata) {
        super(BUsers.class, metadata, "PUBLIC", "USERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("ACTIVE").withIndex(2).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(email, ColumnMetadata.named("EMAIL").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(fullname, ColumnMetadata.named("FULLNAME").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(password, ColumnMetadata.named("PASSWORD").withIndex(5).ofType(Types.VARCHAR).withSize(255));
        addMetadata(registeredat, ColumnMetadata.named("REGISTEREDAT").withIndex(6).ofType(Types.TIMESTAMP).withSize(26).withDigits(6));
        addMetadata(username, ColumnMetadata.named("USERNAME").withIndex(7).ofType(Types.VARCHAR).withSize(255));
    }

}

