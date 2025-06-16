package xio.ilan.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import xio.ilan.H2TestApplication;
import xio.ilan.config.DbConfig;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {H2TestApplication.class})
@Import({DbConfig.class})
@ActiveProfiles("directory")
@DisplayName("Generate in ALPHA directory")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class GenerateInDirectoryTest {

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {

    }
}
