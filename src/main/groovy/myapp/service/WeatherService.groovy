package myapp.service

import myapp.service.model.CustomWeatherResponse

interface WeatherService {
    CustomWeatherResponse fetchWeather(String city)
}
