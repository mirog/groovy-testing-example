package myapp.service.openweathermap

import myapp.service.exception.AmbiguousCityFoundException
import myapp.service.exception.NoCityFoundException
import myapp.service.model.CustomWeatherResponse
import myapp.service.openweathermap.model.CityWeatherData
import myapp.service.openweathermap.model.Main
import myapp.service.openweathermap.model.OpenWeatherMapResponse
import myapp.service.openweathermap.model.Weather
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class OpenWeatherMapServiceTest extends Specification {
    OpenWeatherMapService openWeatherMapService
    RestTemplate restTempate = Mock(RestTemplate)

    void setup() {
        openWeatherMapService = new OpenWeatherMapService(restTempate, 'someaddress.example.com')
    }

    @Unroll
    def "should fail for city \"#city\" when count matches found"() {
        given:
            restTempate.getForObject(_ as String, OpenWeatherMapResponse) >>
                    new OpenWeatherMapResponse(
                            count: count
                    )

        when:
            openWeatherMapService.fetchWeather(city)

        then:
            thrown(exception)

        where:
            city                | count || exception
            'Not existing city' | 0     || NoCityFoundException
            'Which of them'     | 10    || AmbiguousCityFoundException
    }

    @Unroll
    def "should return \"#expectedWeather and #expectedTemperature\" when \"#weather\" weather and #temperature'C"() {
        given:
            restTempate.getForObject(_ as String, OpenWeatherMapResponse) >>
                new OpenWeatherMapResponse(
                        count: 1,
                        list: [
                                new CityWeatherData(
                                        main: new Main(temp: temperature),
                                        weather: [
                                            new Weather(main: weather)
                                        ]
                                )
                        ]
                )
        expect:
            openWeatherMapService.fetchWeather('Some city') == new CustomWeatherResponse(
                        weather: expectedWeather,
                        temperature: expectedTemperature)

        where:
            temperature | weather       || expectedTemperature | expectedWeather
            -2          | 'Snowy'       || 'cold'              | 'snowy'
            10          | 'Cloudy'      || 'warm'              | 'cloudy'
            30          | 'Sunny'       || 'hot'               | 'sunny'
    }
}
