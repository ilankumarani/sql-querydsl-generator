package xio.ilan.test;

import xio.ilan.sql.query.dsl.BStudent;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseTest {

    public static Timestamp timestamp = Timestamp.valueOf("2025-06-15 12:34:56");
    public static Date date = Date.valueOf("2025-06-15");
    public static Time time = Time.valueOf("12:34:56");

    public static BStudent student = BStudent.builder()
            .blogDate(timestamp)
            .blogInstant(timestamp)
            .blogLocalDate(date)
            .blogLocalDateTime(timestamp)
            .blogLocalTime(time)
            .blogOffsetDateTime(timestamp)
            .blogSqlDate(date)
            .blogSqlTime(time)
            .blogSqlTimestamp(timestamp)
            .blogZonedDateTime(timestamp)
            .category("Education")
            .content("This is a sample blog about SQL and Java time types.")
            .id(123L)
            .title("Understanding Java SQL Time APIs")
            .build();

    public static List<BStudent> getStudents() {
        return IntStream.rangeClosed(1, 50)
                .mapToObj(i -> BStudent.builder()
                        .blogDate(timestamp)
                        .blogInstant(timestamp)
                        .blogLocalDate(date)
                        .blogLocalDateTime(timestamp)
                        .blogLocalTime(time)
                        .blogOffsetDateTime(timestamp)
                        .blogSqlDate(date)
                        .blogSqlTime(time)
                        .blogSqlTimestamp(timestamp)
                        .blogZonedDateTime(timestamp)
                        .category("Category " + i)
                        .content("Content for student " + i)
                        .id((long) i)
                        .title("Title " + i)
                        .build()
                )
                .collect(Collectors.toList());
    }
}
