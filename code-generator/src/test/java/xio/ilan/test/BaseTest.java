package xio.ilan.test;

import xio.ilan.sql.query.dsl.BComments;
import xio.ilan.sql.query.dsl.BPosts;
import xio.ilan.sql.query.dsl.BStudent;
import xio.ilan.sql.query.dsl.BUsers;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
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

    public void test() {
        int usersCount = 10;
        int postsPerUser = 100;
        int commentsPerPost = 100;

        List<BUsers> users = new ArrayList<>();
        List<BPosts> posts = new ArrayList<>();
        List<BComments> comments = new ArrayList<>();

        long postId = 1;
        long commentId = 1;

        for (int i = 1; i <= usersCount; i++) {
            // Create User
            BUsers user = BUsers.builder()
                    .id((long) i)
                    .username("user" + i)
                    .fullname("User " + i)
                    .email("user" + i + "@example.com")
                    .password("pass" + i)
                    .registeredat(Timestamp.from(Instant.now()))
                    .active(true)
                    .build();
            users.add(user);

            for (int j = 1; j <= postsPerUser; j++) {
                // Create Post
                BPosts post = BPosts.builder()
                        .id(postId)
                        .title("Post " + j + " by user" + i)
                        .content("Content of post " + j)
                        .category("Category " + (j % 5))
                        .createdat(Timestamp.from(Instant.now()))
                        .updatedat(Timestamp.from(Instant.now()))
                        .published(j % 2 == 0)
                        .userId(user.getId())
                        .build();
                posts.add(post);

                // Create Comments
                for (int k = 1; k <= commentsPerPost; k++) {
                    BComments comment = BComments.builder()
                            .id(commentId++)
                            .author("Commenter " + k)
                            .email("commenter" + k + "@mail.com")
                            .text("Comment " + k + " on post " + postId)
                            .postedat(Timestamp.from(Instant.now()))
                            .approved(k % 2 == 0)
                            .postId(postId)
                            .build();
                    comments.add(comment);
                }

                postId++;
            }
        }

    }
}
