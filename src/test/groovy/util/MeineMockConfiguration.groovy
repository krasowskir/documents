package util

import com.example.documents.model.Account
import com.example.documents.service.AccountService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import spock.mock.DetachedMockFactory

@Configuration
class MeineMockConfiguration {

    @TestConfiguration
    private static class Mocks{
        def factory = new DetachedMockFactory()
        @Bean(name = "accountService")
        AccountService getMockedAccountServ(){
            return factory.Mock(AccountService)
        }
    }
}