package xio.ilan.service;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xio.ilan.sql.query.dsl.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QueryDslService {

    private final SQLQueryFactory sqlQueryFactory;

    public void loadData() {
        SBlogDetails sBlogDetails = SBlogDetails.blogDetails;
        SQLInsertClause insert = sqlQueryFactory.insert(sBlogDetails);
        log.info("############ Load Data ############");
        extracted(insert);
        insert.execute();
    }

    private List<BBlogDetails> extracted(SQLInsertClause insert) {
        List<BBlogDetails> blogDetailsList = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> {
                    BBlogDetails blogDetails = BBlogDetails.builder()
                            .id((long) i)
                            .title("Title " + i)
                            .content("Content for blog post " + i)
                            .category("Category " + (i % 5)) // Cycle through 5 categories
                            .blogDate(new java.util.Date())
                            .blogInstant(Instant.now())
                            .blogLocalDate(LocalDate.now())
                            .blogLocalDateTime(LocalDateTime.now())
                            .blogLocalTime(LocalTime.now())
                            .blogOffsetDateTime(OffsetDateTime.now())
                            .blogSqlDate(Date.valueOf(LocalDate.now()))
                            .blogSqlTime(Time.valueOf(LocalTime.now()))
                            .blogSqlTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                            .blogZonedDateTime(ZonedDateTime.now())
                            .build();
                    insert.populate(blogDetails).addBatch();
                    return blogDetails;
                })
                .collect(Collectors.toList());

        return blogDetailsList;
    }
}
