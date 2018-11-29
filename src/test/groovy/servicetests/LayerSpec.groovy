package servicetests

import com.example.documents.DemoApplication
import com.example.documents.controller.AccountController;
import com.example.documents.model.Account
import com.example.documents.model.AccountDto
import com.example.documents.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import spock.lang.Ignore
import spock.lang.Specification
import util.MeineMockConfiguration;

import java.util.UUID;

@EnableJpaRepositories(basePackages = "com.example.documents")
@ComponentScan(basePackages = "com.example.documents", excludeFilters  = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AccountService.class ))
@SpringBootTest(classes =  [DemoApplication.class, MeineMockConfiguration.class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LayerSpec extends Specification{

    @Autowired
    AccountService accountService

    @Autowired
    AccountController accountController

    @Autowired
    TestRestTemplate template

    @Ignore
    def 'wenn ich den controller aufrufe, kann ich dann das repository intercepten'(){
        given: 'account'
        AccountDto account = new AccountDto()
        account.setName("testi test");
        account.setAlter("27")
        account.setTelefonNummer("015112345")
        account.setEmail("test@testi.de")


        when: 'invoking the ctontroller'
        template.postForLocation('/api/accountPost', account)

        then: 'the service is called'
        1 * accountService.saveAccount(_)
    }

    @Ignore
    def 'ein normaler Spring boot test testet interaktion der beans'(){

        given:
        System.out.println(" in tests: accountController: " + accountController.toString() + " \n accountService " + accountService.toString())

        when:
        accountController.getAllAccounts()

        then:
        1 * accountService.getAllAccounts()
    }
}
