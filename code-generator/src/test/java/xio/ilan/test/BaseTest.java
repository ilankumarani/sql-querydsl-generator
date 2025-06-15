package xio.ilan.test;

import xio.ilan.sql.query.dsl.BStudent;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseTest {


    public static BStudent student = BStudent.builder()
            .blogDate(new Timestamp(System.currentTimeMillis()))
            .blogInstant(new Timestamp(System.currentTimeMillis()))
            .blogLocalDate(new Date(System.currentTimeMillis()))
            .blogLocalDateTime(new Timestamp(System.currentTimeMillis()))
            .blogLocalTime(new Time(System.currentTimeMillis()))
            .blogOffsetDateTime(new Timestamp(System.currentTimeMillis()))
            .blogSqlDate(Date.valueOf("2025-06-15"))
            .blogSqlTime(Time.valueOf("12:34:56"))
            .blogSqlTimestamp(Timestamp.valueOf("2025-06-15 12:34:56"))
            .blogZonedDateTime(Timestamp.valueOf("2025-06-15 15:30:00"))
            .category("Education")
            .content("This is a sample blog about SQL and Java time types.")
            .id(123L)
            .title("Understanding Java SQL Time APIs")
            .build();

    public static List<BStudent> getStudents() {
        return IntStream.rangeClosed(1, 50)
                .mapToObj(i -> BStudent.builder()
                        .blogDate(new Timestamp(System.currentTimeMillis() + i * 1000L))
                        .blogInstant(new Timestamp(System.currentTimeMillis() + i * 2000L))
                        .blogLocalDate(new Date(System.currentTimeMillis() + i * 86400000L))
                        .blogLocalDateTime(new Timestamp(System.currentTimeMillis() + i * 1000L))
                        .blogLocalTime(new Time(System.currentTimeMillis() + i * 1000L))
                        .blogOffsetDateTime(new Timestamp(System.currentTimeMillis() + i * 3000L))
                        .blogSqlDate(Date.valueOf("2025-06-15"))
                        .blogSqlTime(Time.valueOf("12:34:56"))
                        .blogSqlTimestamp(Timestamp.valueOf("2025-06-15 12:34:56"))
                        .blogZonedDateTime(Timestamp.valueOf("2025-06-15 15:30:00"))
                        .category("Category " + i)
                        .content("Content for student " + i)
                        .id((long) i)
                        .title("Title " + i)
                        .build()
                )
                .collect(Collectors.toList());
    }
}
