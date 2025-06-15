package xio.ilan.service;


import com.querydsl.sql.SQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xio.ilan.sql.query.dsl.SBlogDetails;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QueryDslSqlService {

    private final SQLQueryFactory sqlQueryFactory;
    private final SBlogDetails sBlogDetails = SBlogDetails.blogDetails;

    public void test(){

        sqlQueryFactory.select(sBlogDetails.content)
                .from(sBlogDetails)
                .fetch();
    }
}
