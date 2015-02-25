package myapp.service.openweathermap.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class CityWeatherData {
    Integer id
    String name
    Coord coord
    Main main
    Integer dt
    Wind wind
    Sys sys
    Drop rain
    Drop snow
    Clouds all
    List<Weather> weather
}
