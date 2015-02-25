package myapp.service.openweathermap.model

import groovy.transform.Canonical

@Canonical
class OpenWeatherMapResponse {
    String message
    String code
    Integer count
    List<CityWeatherData> list
}
