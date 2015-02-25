package myapp.service.openweathermap.model

import groovy.transform.Canonical

@Canonical
class Wind {
    Float speed
    Float deg
}