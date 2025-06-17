package xio.ilan.test;

import com.ilan.entity.QComment;
import com.ilan.entity.QPost;
import com.ilan.entity.QUser;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import xio.ilan.H2TestApplication;
import xio.ilan.config.DbConfig;
import xio.ilan.service.QueryDslPaginationService;
import xio.ilan.sql.query.dsl.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xio.ilan.service.QueryDslPaginationService.POSTS_PER_USER;
import static xio.ilan.service.QueryDslPaginationService.USERS_COUNT;

/*
@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {H2TestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"query.dsl.sql.generation.enabled=false", "server.port=8080"})
*/
@Disabled
@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {H2TestApplication.class}, properties = {"query.dsl.sql.generation.enabled=false"})
@Import({DbConfig.class})
@DisplayName("QueryDsl pagination")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueryDslPaginationTest extends BaseTest {

    private final QueryDslPaginationService queryDslPaginationService;
    private final QUser qUser = QUser.user;
    private final QPost qPost = QPost.post;
    private final QComment qComment = QComment.comment;
    private final EntityManager entityManager;
    private JPAQuery jpaQuery;


    @BeforeAll
    public void initLoadData() {
        jpaQuery = new JPAQuery<>(entityManager);
        queryDslPaginationService.dataLoad();
    }

    @Order(1)
    @Test
    public void isDataLoaded() {
        assertEquals(USERS_COUNT, jpaQuery.select(qUser.id.count())
                .from(qUser).fetchCount());
        assertEquals(USERS_COUNT * USERS_COUNT * POSTS_PER_USER, jpaQuery.select(qPost.id.count())
                .from(qPost).fetchCount());
        assertEquals(USERS_COUNT * USERS_COUNT * USERS_COUNT * POSTS_PER_USER * POSTS_PER_USER, jpaQuery.select(qComment.id.count())
                .from(qComment).fetchCount());
    }

}
