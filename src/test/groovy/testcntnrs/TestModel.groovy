package testcntnrs


import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.Specification
import testcntnrs.model.Group
import testcntnrs.model.GroupRepo
import testcntnrs.model.User
import testcntnrs.model.UserRepo

import javax.persistence.EntityManager

//@SpringBootTest(classes = [Testcontainers01Application])
//@SpringBootTest(classes = [Testcontainers01Application])
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
class TestModel extends Specification {

    @Autowired
    TestEntityManager testEntityManager

    @Autowired private EntityManager entityManager

    @Autowired
    PlatformTransactionManager transactionManager

    @Autowired
    UserRepo userRepo

    @Autowired
    GroupRepo groupRepo

    @Test
    def "model test"() {
        given:
        def a = "test!!!"

        when:
        Group g = new Group()
        g.name = "admini"
        groupRepo.save(g)
        Group g2 = new Group()
        g2.name = "userzy"
        groupRepo.save(g2)
        User u = new User();
        u.login = "login003"
        u.groups = Set.of(g)
        transactionManager.commit()
        println "test!!!"

        then:
        1 == 1
    }
}
