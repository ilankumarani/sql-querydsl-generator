package xio.ilan.test;

import com.ilan.entity.QStudent;
import com.ilan.entity.Student;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Projections;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class}, properties = {"query.dsl.sql.generation.enabled=false"})
@Import({DbConfig.class})
@DisplayName("Generate for all Schema")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomQueryDslSqlTest extends BaseTest {

    private final SQLQueryFactory sqlQueryFactory;

    // Query DSL JPA generated class
    private final QStudent qStudent = QStudent.student;

    RelationalPath<Student> sqlEntity = QueryDslUtils.asRelational(qStudent);

    private final SDummyStudent dummyStudent = new SDummyStudent(sqlEntity.getMetadata().getName(), sqlEntity.getSchemaName(), sqlEntity.getTableName());

    // Query DSL SQL generated class
    //private final SStudent sstudent = new SStudent(aliasName);

    @Order(1)
    @Test
    public void valueInsert() {
        SQLInsertClause sqlInsertClause = sqlQueryFactory.insert(sqlEntity);
        sqlInsertClause.set(dummyStudent.id, 101L)
                .set(dummyStudent.content, "have to improve");
        assertEquals(sqlInsertClause.getSQL().size(), sqlInsertClause.execute());
        String expected = "insert into STUDENT_SCHEMA.STUDENT (ID, CONTENT) values (101, 'have to improve')";
        assertEquals(expected, sqlInsertClause.getSQL().get(0).getSQL());
    }

    @Order(2)
    @Test
    public void selectById() {
        String selectIdAndLimitOne = "select student.ID from STUDENT_SCHEMA.STUDENT student where student.ID = 101 limit 1";
        SQLQuery<Long> query = sqlQueryFactory.select(dummyStudent.id)
                .from(sqlEntity)
                .where(dummyStudent.id.eq(101L))
                .limit(1);
        assertEquals(101L, query.fetchFirst());
        assertEquals(selectIdAndLimitOne, query.getSQL().getSQL().toString());
    }

    @Order(2)
    @DisplayName("selectFrom does not work is expected")
    @Test
    public void selectFrom() {
        EntityPath<Student> myEntity = qStudent;
        RelationalPath<Student> sqlEntity = QueryDslUtils.asRelational(myEntity);

        assertThatThrownBy(() -> {
            sqlQueryFactory.selectFrom(sqlEntity).fetch();
        }).isInstanceOf(IllegalArgumentException.class) // replace with actual exception type
                .hasMessageContaining("No bindings could be derived from student");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sqlQueryFactory.selectFrom(sqlEntity).fetch();
        });

        assertTrue(exception.getMessage().contains("No bindings could be derived from student"));
    }


    @Test
    public void projectionByBean() {
        List<BStudent> bStudents = sqlQueryFactory.select(Projections.bean(BStudent.class, dummyStudent.id))
                .from(sqlEntity)
                .fetch();

        assertEquals(101L, bStudents.get(0).getId());
    }
}
