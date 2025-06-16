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

    public List<BUsers> generateUsers(int usersCount) {
        return IntStream.rangeClosed(1, usersCount)
                .mapToObj(i ->
                        BUsers.builder()
                                .id((long) i)
                                .username("user" + i)
                                .fullname("User " + i)
                                .email("user" + i + "@example.com")
                                .password("pass" + i)
                                .registeredat(Timestamp.from(Instant.now()))
                                .active(true)
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<BPosts> generatePosts(List<BUsers> users, int postsPerUser) {
        List<BPosts> posts = new ArrayList<>();
        long postId = 1L;
        for (BUsers user : users) {
            for (int j = 1; j <= postsPerUser; j++) {
                BPosts post = BPosts.builder()
                        .id(postId++)
                        .title("Post " + j + " by " + user.getUsername())
                        .content("Content of post " + j)
                        .category("Category " + (j % 5))
                        .createdat(Timestamp.from(Instant.now()))
                        .updatedat(Timestamp.from(Instant.now()))
                        .published(j % 2 == 0)
                        .userId(user.getId())
                        .build();
                posts.add(post);
            }
        }
        return posts;
    }

    public List<BComments> generateComments(List<BPosts> posts, int commentsPerPost) {
        List<BComments> comments = new ArrayList<>();
        long commentId = 1L;
        for (BPosts post : posts) {
            for (int k = 1; k <= commentsPerPost; k++) {
                BComments comment = BComments.builder()
                        .id(commentId++)
                        .author("Commenter " + k)
                        .email("commenter" + k + "@mail.com")
                        .text("Comment " + k + " on post " + post.getId())
                        .postedat(Timestamp.from(Instant.now()))
                        .approved(k % 2 == 0)
                        .postId(post.getId())
                        .build();
                comments.add(comment);
            }
        }
        return comments;
    }
}
