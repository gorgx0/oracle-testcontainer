package testcntnrs;


import org.junit.jupiter.api.Test;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class OracleContainerTest {

    private static final String ORACLE_PWD = "password";

    @Container
    private final static OracleContainer oracle = new OracleContainer(DockerImageName.parse("oracle_xe:18.4.0"))
            .withLogConsumer(outputFrame -> System.out.println("** DOCKER LOG **" + outputFrame.getUtf8String()))
            .withFileSystemBind("./oradata","/opt/oracle/oradata")
            .withPassword(ORACLE_PWD)
            .withEnv("ORACLE_PWD", ORACLE_PWD);

    @Test
    void oracleContainerTest() throws InterruptedException {
        Integer oraclePort = oracle.getOraclePort();
        System.out.println("Oracle port: " + oraclePort);
        System.out.println("Oracle jdbc url: " + oracle.getJdbcUrl());
        Thread.sleep(1000*60*10);
    }
}
