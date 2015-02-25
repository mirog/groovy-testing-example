package jug.spec

import geb.spock.GebReportingSpec
import jug.page.LoginPage
import jug.page.WeatherPage

class WeatherPageSpec extends GebReportingSpec {
    def "show go to weather page for Torun and display results"() {
        when:
            go '/weather'
        then:
            at LoginPage
        when:
            loginField = "Torun"
            passwordField = "user"
            submitButton.click()
        then:
            at WeatherPage
        and:
            mainTitle.text() == 'Weather for Torun'
    }

    def "show go to weather page for Manchester, but without results"() {
        when:
            go '/weather'
        then:
            at LoginPage
        when:
            loginField = "Manchester"
            passwordField = "user"
            submitButton.click()
        then:
            at WeatherPage
        and:
            !mainTitle
    }
}
