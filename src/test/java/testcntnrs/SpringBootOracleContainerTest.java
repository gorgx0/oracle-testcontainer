package testcntnrs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest
@Testcontainers
public class SpringBootOracleContainerTest {

    private static final String ORACLE_PWD = "password";

    @Container
    private final static OracleContainer oracle = new OracleContainer(DockerImageName.parse("oracle_xe:18.4.0"))
            .withLogConsumer(outputFrame -> System.out.println("** DOCKER LOG **" + outputFrame.getUtf8String()))
            .withFileSystemBind("./oradata","/opt/oracle/oradata")
            .withFileSystemBind("./ora_startup","/opt/oracle/scripts/startup")
            .withPassword(ORACLE_PWD)
            .withEnv("ORACLE_PWD", ORACLE_PWD);

    @Autowired
    DataSource dataSource;

    @Test
    void spingBootTest() throws SQLException {
        System.out.println("************** spingBootTest **************");
        System.out.println(dataSource.getConnection().getMetaData().getURL());
        System.out.println("************** spingBootTest(end) **************");
    }


    @TestConfiguration
    static class OracleTestConfiguration {

        @Autowired
        private Environment env;

        @Bean
        DataSource dataSource() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(String.format("jdbc:oracle:thin:@//%s:%d/XEPDB1", oracle.getHost(), oracle.getOraclePort()));
            hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
            hikariConfig.setPassword(env.getProperty("spring.datasource.password"));
            return new HikariDataSource(hikariConfig);
        }
    }
}
