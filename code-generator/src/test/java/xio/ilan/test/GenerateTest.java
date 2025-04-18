package xio.ilan.test;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import xio.ilan.Application;
import xio.ilan.config.DbConfig;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class})
@Import({DbConfig.class})
@DisplayName("Generate for all Schema")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class GenerateTest {


    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {

    }
}
