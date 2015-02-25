package myapp.controller

import myapp.service.WeatherService
import myapp.service.exception.AmbiguousCityFoundException
import myapp.service.exception.NoCityFoundException
import myapp.service.model.CustomWeatherResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import java.security.Principal

@Controller
@RequestMapping('/')
class FrontendController {

    private final WeatherService weatherService

    @Autowired
    FrontendController(WeatherService weatherService) {
        this.weatherService = weatherService
    }

    @RequestMapping('weather')
    String weather(Model model, Principal principal) {
        String city = principal.name
        String error = null
        CustomWeatherResponse weatherResponse = null
        try {
            weatherResponse = weatherService.fetchWeather(city)
        } catch (NoCityFoundException e) {
            error = 'No such city'
        } catch (AmbiguousCityFoundException e) {
            error = 'Too many cities with the same name!'
        }
        model.addAllAttributes(['name': city, 'data': weatherResponse, 'error': error])
        'weather'
    }

    @RequestMapping
    String index() {
        'index'
    }

    @RequestMapping('hello/{name}')
    String hello(Model model, @PathVariable('name') String name) {
        model.addAttribute('name', name.toLowerCase().capitalize())
        'index'
    }
}
