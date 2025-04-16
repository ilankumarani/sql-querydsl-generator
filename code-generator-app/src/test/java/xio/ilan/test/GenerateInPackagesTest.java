package xio.ilan.test;


import com.ilan.QueryDslSqlApplication;
import com.ilan.config.DbConfig;
import com.ilan.shutdown.ShutdownEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {QueryDslSqlApplication.class})
@Import({DbConfig.class})
@ActiveProfiles("packages")
@DisplayName("Generate in package beta.querydsl.sql")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class GenerateInPackagesTest {

    private final ShutdownEndpoint shutdownEndpoint;

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {
        ShutdownEndpoint.ShutdownDescriptor shutdownDescriptor = shutdownEndpoint.shutdown();
        log.info("Application killed :: {}", shutdownDescriptor.getMessage());
    }
}
