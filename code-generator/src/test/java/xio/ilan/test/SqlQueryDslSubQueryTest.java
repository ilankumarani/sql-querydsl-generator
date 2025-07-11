package xio.ilan.test;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import xio.ilan.H2TestApplication;
import xio.ilan.config.DbConfig;
import xio.ilan.service.QueryDslService;
import xio.ilan.sql.query.dsl.*;

import static com.querydsl.core.types.dsl.Expressions.stringPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {H2TestApplication.class})
@Import({DbConfig.class})
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SqlQueryDslSubQueryTest {

    private final ApplicationContext applicationContext;
    private final SQLQueryFactory sqlQueryFactory;
    private final QueryDslService queryDslService;
    private SPosts sPosts = SPosts.posts;
    private SComments sComments = SComments.comments;

    @BeforeAll
    public void init(){
        queryDslService.loadData();
    }

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {
        final String postId = "post_id";
        final String commentText = "comment_text";

        //Alias name for subQuery table
        final StringPath tableAliasName = stringPath("cms");

        // Define subquery
        SQLQuery<Tuple>  subQueryExpression = SQLExpressions.select(sComments.text.as(commentText), sComments.postId.as(postId))
                .from(sComments);
        SQLQuery<Tuple>  subQuery = sqlQueryFactory.select(sComments.text.as(commentText), sComments.postId.as(postId))
                .from(sComments);

        // Access aliased columns from the subquery using Expressions
        StringPath commentTextPath = stringPath(tableAliasName, commentText);
        Expression postIdPath = Expressions.stringPath(tableAliasName, postId);

        // Main query joining with subquery
        SQLQuery<Tuple> mainQuery = sqlQueryFactory.select(sPosts.id, commentTextPath)
                .from(sPosts)
                .leftJoin(subQuery, tableAliasName)
                .on(sPosts.id.eq(postIdPath));

        mainQuery.fetch();
        assertEquals("select POSTS.ID, cms.comment_text from PUBLIC.POSTS POSTS left join (select COMMENTS.TEXT as comment_text, COMMENTS.POST_ID as post_id from PUBLIC.COMMENTS COMMENTS) as cms on POSTS.ID = cms.post_id", mainQuery.getSQL().getSQL());

    }


}






/*final String postId = "post_id";
        final String commentText = "comment_text";
        final String tableAliasName = "cms";

// Define subquery
        SQLQuery<Tuple> commentsSubquery = sqlQueryFactory
                .select(sComments.text.as(commentText), sComments.postId.as(postId))
                .from(sComments);

// Create an alias for the subquery
        SubQueryExpression<Tuple> commentsAlias = commentsSubquery.as(tableAliasName);

// Access aliased columns from the subquery using Expressions
        StringPath commentTextPath = Expressions.stringPath(tableAliasName, commentText);
        StringPath postIdPath = Expressions.stringPath(tableAliasName, postId);

// Main query joining with subquery
        List<Tuple> result = sqlQueryFactory
                .select(sPosts.id, commentTextPath)
                .from(sPosts)
                .leftJoin(commentsAlias, Expressions.path(Object.class, tableAliasName))
                .on(sPosts.id.eq(postIdPath))
                .fetch();*/