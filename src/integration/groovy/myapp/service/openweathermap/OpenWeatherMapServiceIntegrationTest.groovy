package myapp.service.openweathermap

import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import myapp.Application
import myapp.service.model.CustomWeatherResponse
import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo

@ContextConfiguration(classes = Application)
@Configuration
@PropertySource('classpath:integration.properties')
class OpenWeatherMapServiceIntegrationTest extends Specification {

    @Shared @ClassRule
    WireMockClassRule wireMockRule = new WireMockClassRule(8089)

    @Autowired
    OpenWeatherMapService openWeatherMapService

    def "should return proper custom weather response"() {
        given:
            stubFor(get(urlPathEqualTo('/data/2.5/find?q=Torun&units=metric'))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader('Content-Type', 'application/json')
                .withBody(output)))

        when:
            def result = openWeatherMapService.fetchWeather('Torun')

        then:
            result == new CustomWeatherResponse(weather: weather, temperature: temperature)

        where:
            output              || weather  | temperature
            CLOUDY_COLD_TORUN   || 'clouds' | 'cold'
    }

    static final def CLOUDY_COLD_TORUN = '{"message":"accurate","cod":"200","count":1,"list":[{"id":3083271,"name":"Torun","coord":{"lon":18.598141,"lat":53.013748},"main":{"temp":4.07,"humidity":79.47,"pressure":1012.7,"temp_min":4.07,"temp_max":4.07},"dt":1424640181,"wind":{"speed":2.01,"deg":208.003},"sys":{"country":"PL"},"rain":{"3h":0},"snow":{"3h":0},"clouds":{"all":80},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04n"}]}]}'
}
