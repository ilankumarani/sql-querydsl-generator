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
import xio.ilan.Application;
import xio.ilan.config.DbConfig;
import xio.ilan.service.QueryDslService;
import xio.ilan.sql.query.dsl.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class}, properties = {"query.dsl.sql.generation.enabled=false"})
@Import({DbConfig.class})
@DisplayName("QueryDsl pagination")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueryDslPaginationTest extends BaseTest {

    private final QueryDslService queryDslService;
    private final QUser qUser = QUser.user;
    private final QPost qPost = QPost.post;
    private final QComment qComment = QComment.comment;
    private final EntityManager entityManager;
    private JPAQuery jpaQuery;


    @Order(1)
    @Test
    public void initLoadData() {
        jpaQuery = new JPAQuery<>(entityManager);
        List<BUsers> users = generateUsers(10);
        queryDslService.bulkInsertUsers(users);
//        List<BPosts> posts = generatePosts(users, 100);
//        queryDslService.bulkInsertPosts(posts);
//        List<BComments> comments = generateComments(posts, 100);
//        queryDslService.bulkInsertComments(comments);
    }

    @Order(2)
    //@Test
    public void isDataLoaded() {
        assertEquals(10, jpaQuery.select(qUser.id.count())
                .from(qUser).fetch().size());
        assertEquals(10, jpaQuery.select(qPost.id.count())
                .from(qPost).fetch().size());
        assertEquals(10, jpaQuery.select(qComment.id.count())
                .from(qComment).fetch().size());
    }

}
