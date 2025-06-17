package xio.ilan.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xio.ilan.sql.query.dsl.*;
import xio.ilan.test.BaseTest;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueryDslPaginationService extends BaseTest {

    public static final int USERS_COUNT = 5;
    public static final int POSTS_PER_USER = 25;
    public static final int COMMENTS_PER_POST = 1;
    private final SQLQueryFactory sqlQueryFactory;
    private final SUsers sUsers = SUsers.users;
    private final SPosts sPosts = SPosts.posts;
    private final SComments sComments = SComments.comments;
    private JPAQuery jpaQuery;
    @PersistenceContext
    private EntityManager entityManager;

    public void init(){
        jpaQuery = new JPAQuery<>(entityManager);
    }

    public void dataLoad() {
        jpaQuery = new JPAQuery<>(entityManager);
        List<BUsers> users = generateUsers(USERS_COUNT);
        bulkInsertUsers(users);
        List<BPosts> posts = generatePosts(users, POSTS_PER_USER);
        bulkInsertPosts(posts);
        List<BComments> comments = generateComments(posts, COMMENTS_PER_POST);
        bulkInsertComments(comments);
    }

    @SneakyThrows
    public void bulkInsertUsers(List<BUsers> users) {
        SQLInsertClause insert = sqlQueryFactory.insert(sUsers);
        for (BUsers buser : users) {
            insert.populate(buser).addBatch();
        }
        insert.execute();
    }


    @SneakyThrows
    public void bulkInsertPosts(List<BPosts> posts) {
        SQLInsertClause insert = sqlQueryFactory.insert(sPosts);

        for (BPosts post : posts) {
            insert.populate(post)
                    .addBatch();
        }
        insert.execute();
    }


    @SneakyThrows
    public void bulkInsertComments(List<BComments> comments) {
        SQLInsertClause insert = sqlQueryFactory.insert(sComments);
        for (BComments comment : comments) {
            insert.populate(comment)
                    .addBatch();
        }
        insert.execute();
    }
}
