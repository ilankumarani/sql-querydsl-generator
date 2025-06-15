package xio.ilan.test;

import com.ilan.entity.QStudent;
import com.ilan.entity.Student;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import io.ilan.util.QueryDslUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import xio.ilan.Application;
import xio.ilan.config.DbConfig;
import xio.ilan.sql.query.dsl.BStudent;
import xio.ilan.sql.query.dsl.SStudent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class})
@Import({DbConfig.class})
@DisplayName("Generate for all Schema")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class AdmireQueryDslSqlTest {

    private final SQLQueryFactory sqlQueryFactory;
    // Query DSL JPA generated class
    private final QStudent qStudent = QStudent.student;

    // Query DSL SQL generated class
    private final SStudent sstudent = SStudent.student;

    RelationalPath<Student> sqlEntity = QueryDslUtils.asRelational(qStudent);

    @Test
    public void testA(){
        SQLQuery<Long> query = sqlQueryFactory.select(sstudent.id).from(sqlEntity);
        query.fetch();
        assertEquals("select STUDENT.ID from STUDENT_SCHEMA_ALPHA.STUDENT_TABLE_ALPHA student", query.getSQL().getSQL().toString());
    }

    @DisplayName("selectFrom does not work is expected")
    @Test
    public void selectFrom(){
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
    public void projectionByBean(){
        sqlQueryFactory.select(Projections.bean(BStudent.class, sstudent.id))
                .from(sqlEntity).fetch();
    }
}
