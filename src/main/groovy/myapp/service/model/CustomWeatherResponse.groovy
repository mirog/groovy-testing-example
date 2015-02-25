package myapp.service.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class CustomWeatherResponse {
    String weather
    String temperature
}
