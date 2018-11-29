package servicetests

import com.example.documents.DemoApplication
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import util.MeineMockConfiguration

@SpringBootTest(classes =  [DemoApplication.class, MeineMockConfiguration.class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndpointSpec extends Specification {

    @Autowired
    TestRestTemplate template

    def jsonSlurper = new JsonSlurper()

    def 'actuator endpoints are available'() {
        when:
        ResponseEntity<String> result = template.getForEntity('/actuator/health', String.class)

        then:
        result.statusCode == HttpStatus.OK
        def jsonBody = jsonSlurper.parseText(result.body)
        jsonBody instanceof Map
        jsonBody.status == "UP"
    }
}