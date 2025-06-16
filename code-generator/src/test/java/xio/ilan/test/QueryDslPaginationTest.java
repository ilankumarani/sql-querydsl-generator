package xio.ilan.test;

import com.ilan.entity.QUser;
import com.querydsl.sql.SQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import xio.ilan.Application;
import xio.ilan.config.DbConfig;
import xio.ilan.sql.query.dsl.*;

import java.util.List;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class})
@Import({DbConfig.class})
@DisplayName("Generate for all Schema")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueryDslPaginationTest extends BaseTest{

    private final SQLQueryFactory sqlQueryFactory;
    private final SUsers sUsers = SUsers.users;
    private final SPosts sPosts = SPosts.posts;
    private final SComments sComments = SComments.comments;


    @Order(1)
    public void loadData(){
        List<BUsers> users = generateUsers(10);
        List<BPosts> posts = generatePosts(users, 100);
        List<BComments> comments = generateComments(posts, 100);
    }


}
