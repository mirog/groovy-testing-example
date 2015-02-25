package myapp.service.openweathermap.model

import groovy.transform.Canonical

@Canonical
class Main {
    Float temp
    Float humidity
    Float pressure
    Float temp_min
    Float temp_max
}