package yov.ilan;

import com.ilan.QueryDslSqlApplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE, classes = {QueryDslSqlApplication.class})
public class SchemaCreationTest {

    private final DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void isSchemaCreated() {
        List<String> schemaNames = jdbcTemplate.queryForList("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA", String.class);
        assertNotNull(schemaNames);
    }

    @Test
    public void schemaFound() {
        List<String> schemaNames = jdbcTemplate.queryForList("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA", String.class);
        List<String> schemasFound = schemaNames.stream()
                .filter(schemaName -> schemaName.equals("CHANNEL_SCHEMA") || schemaName.equals("BBC_SCHEMA"))
                .collect(Collectors.toList());
        assertEquals(2, schemasFound.size());
        assertTrue(schemasFound.contains("CHANNEL_SCHEMA"));
        assertTrue(schemasFound.contains("BBC_SCHEMA"));
    }
}
