package servicetests

import com.example.documents.DemoApplication
import com.example.documents.model.Account
import com.example.documents.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import spock.lang.Specification

@EnableJpaRepositories(basePackages = "com.example.documents")
@SpringBootTest(classes = DemoApplication.class)
class PersistenceSpec extends Specification {

    @Autowired
    AccountRepository accountRepository

    def setup(){

    }

    def 'ein Account kann in der DB gespeichert werden'(){

        given: 'an account wiht customer information'
        Account account = new Account()
        UUID accountId = UUID.randomUUID()
        account.setEmailAdresse("test@testi.de")
        account.setNachname("testi")
        account.setVorname("test")
        account.setTelefonNummer("015112345")
        account.setId(accountId)
        System.out.println("id: " + accountId)

        when: 'persisting the account'
        accountRepository.save(account)

        then: 'account should be able to be found by uuid'
        Account foundAcc = accountRepository.findById(accountId).get()

        foundAcc.emailAdresse == account.emailAdresse
        foundAcc.vorname == account.vorname
        foundAcc.nachname == account.nachname
        foundAcc.telefonNummer == account.telefonNummer
    }
}