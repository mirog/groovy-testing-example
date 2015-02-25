package myapp.service.openweathermap.model

import groovy.transform.Canonical

@Canonical
class Weather {
    Integer id
    String main
    String description
    String icon
}