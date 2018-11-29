package servicetests

import com.example.documents.DemoApplication
import com.example.documents.controller.AccountController
import com.example.documents.model.Account
import com.example.documents.model.Identity
import com.example.documents.repository.AccountRepository
import com.example.documents.repository.IdentityRepository
import com.example.documents.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.Rollback
import spock.lang.Specification
import spock.lang.Unroll

//DirtiesContext affects only the applicationContext cache, not db state
@EnableJpaRepositories(basePackages = "com.example.documents")
@SpringBootTest(classes = [DemoApplication.class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersistenceSpec extends Specification {


    @Autowired
    AccountService accountService

    @Autowired
    AccountRepository accountRepository

    @Autowired
    IdentityRepository identityRepository

    def cleanup(){

        //in this order, because of foreign-key relationship
        identityRepository.deleteAll();
        accountRepository.deleteAll();
    }

    //@rollback, does not work
    def 'ein Account kann in der DB gespeichert werden'() {

        given: 'an account wiht customer information'
        Account account = new Account()
        UUID accountId = UUID.randomUUID()
        account.setEmailAdresse("test@testi.de")
        account.setName("Richard Krasowski")
        account.setTelefonNummer("015112345")
        account.setId(accountId)
        System.out.println("id: " + accountId)

        when: 'persisting the account'
        accountService.saveAccount(account)

        then: 'account should be able to be found by uuid'
        Account foundAcc = accountRepository.findById(accountId).get()

        foundAcc.emailAdresse == account.emailAdresse
        foundAcc.name == account.name
        foundAcc.alter == account.alter
        foundAcc.telefonNummer == account.telefonNummer
    }

    def ' eine identity kann gepsiechert werden'() {
        given:
        Identity meineId = new Identity()
        meineId.setUsername("richard")
        meineId.setPassword("test123")

        and:
        Account account = new Account()
        UUID accountId = UUID.randomUUID()
        account.setEmailAdresse("test@testi.de")
        account.setName("Richard Krasowski")
        account.setTelefonNummer("015112345")
        account.setId(accountId)

        meineId.setAccount(account)

        when:
        identityRepository.save(meineId)

        then:
        identityRepository.findById("richard") != null

        and:
        def foundAccount = identityRepository.findById("richard").get().getAccount()
        foundAccount.name == "Richard Krasowski"
        foundAccount.telefonNummer == "015112345"

    }
}