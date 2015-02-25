package myapp.controller

import myapp.Application
import myapp.service.openweathermap.OpenWeatherMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
@WebAppConfiguration
@IntegrationTest
class FrontendControllerIntegrationTest extends Specification {

    @Autowired
    OpenWeatherMapService openWeatherMapService

    def "should return status 200 for the index page"() {
        setup:
            RestTemplate template = new TestRestTemplate()

        when:
            ResponseEntity<String> response = template.getForEntity('http://localhost:8080', String.class)

        then:
            response.getStatusCode().value() == 200
    }
}
