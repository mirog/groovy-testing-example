package myapp.service.openweathermap.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class Drop {
    @JsonProperty(value = "3h")
    String threeH
}