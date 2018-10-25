package servicetests

import com.example.documents.DemoApplication
import com.example.documents.controller.AccountDTO
import com.example.documents.model.Account
import com.example.documents.repository.AccountRepository
import com.example.documents.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.HttpEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@EnableJpaRepositories(basePackages = "com.example.documents")
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersistenceSpec extends Specification {


    @Autowired
    AccountService accountService

    @Autowired
    TestRestTemplate template

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
    def 'wenn ich den controller aufrufe, kann ich dann das repository intercepten'(){
        given: 'account'
        Account account = new Account()
        UUID accountId = UUID.randomUUID()
        account.setEmailAdresse("test@testi.de")
        account.setNachname("testi")
        account.setVorname("test")
        account.setTelefonNummer("015112345")
        account.setId(accountId)

        AccountDTO accountDTO = new AccountDTO()
        UUID accountId2 = UUID.randomUUID()
        accountDTO.setEmailAdresse("test@testi.de")
        accountDTO.setNachname("testi")
        accountDTO.setVorname("test")
        accountDTO.setTelefonNummer("015112345")
        accountDTO.setId(accountId2)
        
        when: 'invoking the ctontroller'
        template.postForLocation('/accountPost', accountDTO)

        then: 'the service is called'
        1 * accountService.saveAccount(_)
    }
}