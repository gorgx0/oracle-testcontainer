package testcntnrs;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.OracleContainer;

@SpringBootTest
class Testcontainers01ApplicationTests {

    @ClassRule
    public OracleContainer oracle = new OracleContainer("oracle_xe:18.4.0");

    @Test
    void contextLoads() {
    }

}
