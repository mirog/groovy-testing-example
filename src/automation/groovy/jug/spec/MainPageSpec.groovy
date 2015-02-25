package jug.spec

import geb.spock.GebReportingSpec
import jug.page.LoginPage
import jug.page.MainPage

class MainPageSpec extends GebReportingSpec {
    def "should show items"() {
        when:
            go '/'
        then:
            at MainPage
        and:
            mainText.text().startsWith('Hi')
    }

    def "should go to login page when login link clicked"() {
        given:
            to MainPage
        when:
            menuBar.loginLink.click()
        then:
            at LoginPage
    }
}
