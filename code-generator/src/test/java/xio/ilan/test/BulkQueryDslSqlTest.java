package xio.ilan.test;

import com.ilan.entity.QStudent;
import com.ilan.entity.Student;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import io.ilan.util.QueryDslUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import xio.ilan.Application;
import xio.ilan.config.DbConfig;
import xio.ilan.sql.query.dsl.BStudent;
import xio.ilan.sql.query.dsl.SDummyStudent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class})
@Import({DbConfig.class})
@DisplayName("Generate for all Schema")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BulkQueryDslSqlTest extends BaseTest {

    private final SQLQueryFactory sqlQueryFactory;

    private final String aliasName = "student";

    // Query DSL JPA generated class
    private final QStudent qStudent = new QStudent(aliasName);

    RelationalPath<Student> sqlEntity = QueryDslUtils.asRelational(qStudent);

    private final SDummyStudent dummyStudent = new SDummyStudent(aliasName, sqlEntity.getSchemaName(), sqlEntity.getTableName());

    // Query DSL SQL generated class
    //private final SStudent sstudent = new SStudent(aliasName);


    @Order(1)
    @Test
    public void populateInsert() {
        SQLInsertClause sqlInsertClause = sqlQueryFactory.insert(dummyStudent);
        SQLInsertClause populate = sqlInsertClause.populate(BaseTest.student);
        Long insertedCount = populate.execute();
        assertEquals(1, insertedCount);

        assertEquals("insert into STUDENT_SCHEMA.STUDENT (BLOG_DATE, BLOG_INSTANT, BLOG_LOCAL_DATE, BLOG_LOCAL_DATE_TIME, BLOG_LOCAL_TIME, BLOG_OFFSET_DATE_TIME, BLOG_SQL_DATE, BLOG_SQL_TIME, BLOG_SQL_TIMESTAMP, BLOG_ZONED_DATE_TIME, CATEGORY, CONTENT, ID, TITLE) values ((timestamp '2025-06-16 00:00:36'), (timestamp '2025-06-16 00:00:36'), (date '2025-06-16'), (timestamp '2025-06-16 00:00:36'), (time '00:00:36'), (timestamp '2025-06-16 00:00:36'), (date '2025-06-15'), (time '12:34:56'), (timestamp '2025-06-15 12:34:56'), (timestamp '2025-06-15 15:30:00'), 'Education', 'This is a sample blog about SQL and Java time types.', 123, 'Understanding Java SQL Time APIs')", sqlInsertClause.getSQL().get(0).getSQL());
    }


    @Order(2)
    @DisplayName("select * From")
    @Test
    public void selectFrom() {
        SQLQuery<BStudent> studentSQLQuery = sqlQueryFactory.selectFrom(dummyStudent);
        assertEquals("select student.BLOG_DATE, student.BLOG_INSTANT, student.BLOG_LOCAL_DATE, student.BLOG_LOCAL_DATE_TIME, student.BLOG_LOCAL_TIME, student.BLOG_OFFSET_DATE_TIME, student.BLOG_SQL_DATE, student.BLOG_SQL_TIME, student.BLOG_SQL_TIMESTAMP, student.BLOG_ZONED_DATE_TIME, student.CATEGORY, student.CONTENT, student.ID, student.TITLE from STUDENT_SCHEMA.STUDENT student", studentSQLQuery.getSQL().getSQL().toString());
        assertEquals(1, studentSQLQuery.fetch());
    }

}
