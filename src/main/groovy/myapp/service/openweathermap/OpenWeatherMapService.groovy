package myapp.service.openweathermap

import myapp.service.WeatherService
import myapp.service.exception.AmbiguousCityFoundException
import myapp.service.exception.NoCityFoundException
import myapp.service.model.CustomWeatherResponse
import myapp.service.openweathermap.model.OpenWeatherMapResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class OpenWeatherMapService implements WeatherService {

    private RestTemplate restTemplate
    private String server

    @Autowired
    OpenWeatherMapService (RestTemplate restTemplate, @Value('${openweathermap.server}') String server) {
        this.restTemplate = restTemplate
        this.server = server
    }

    CustomWeatherResponse fetchWeather(String city) {
        def result = restTemplate.getForObject(
                    "http://${server}/data/2.5/find?q=${city}&units=metric",
                    OpenWeatherMapResponse)
        if (result.count == 0) {
            throw new NoCityFoundException()
        }
        if (result.count > 1) {
            throw new AmbiguousCityFoundException()
        }
        buildWeatherResponse(result)
    }

    private static CustomWeatherResponse buildWeatherResponse(OpenWeatherMapResponse mapResponse) {
        new CustomWeatherResponse(
                temperature: buildTemperature(mapResponse),
                weather: mapResponse.list.head().weather.head().main.toLowerCase()
        )
    }

    private static String buildTemperature(OpenWeatherMapResponse mapResponse) {
        def temp = mapResponse.list.head().main.temp
            if (temp < 5) 'cold'
            else if (temp >= 5 && temp < 20) 'warm'
            else 'hot'
    }
}
