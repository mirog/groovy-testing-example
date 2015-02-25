package myapp.controller

import myapp.service.WeatherService
import org.springframework.ui.Model
import spock.lang.Specification
import spock.lang.Unroll


class FrontendControllerTest extends Specification {
    FrontendController frontendController
    WeatherService weatherService = Mock(WeatherService)

    void setup() {
       frontendController = new FrontendController(weatherService)
    }

    def "should return index template when index action called"() {
        when:
            def result = frontendController.index()

        then:
            result == "index"
    }

    def "should return index template with capitalized name property"() {
        given:
            def model = Mock(Model)

        when:
            def result = frontendController.hello(model, "miro")

        then:
           1 * model.addAttribute("name", "Miro")
           result == "index"
    }

    def "should also return index template with capitalized name property"() {
        given:
            def model = Mock(Model)
            1 * model.addAttribute("name", expectedName)

        expect:
            viewName == frontendController.hello(model, name)

        where:
            name        || expectedName | viewName
            'Miro'      || 'Miro'       | 'index'
            'mIRek'     || 'Mirek'      | 'index'
            'miroslaw'  || 'Miroslaw'   | 'index'
    }

    @Unroll
    def "should return index template with capitalized name property in separate case"() {
        given:
            def model = Mock(Model)
            1 * model.addAttribute("name", expectedName)

        expect:
            viewName == frontendController.hello(model, name)

        where:
            name        || expectedName | viewName
            'Miro'      || 'Miro'       | 'index'
            'mIRek'     || 'Mirek'      | 'index'
            'miroslaw'  || 'Miroslaw'   | 'index'
    }

    @Unroll
    def "should return #viewName template with #expectedName property when called with #name param"() {
        given:
            def model = Mock(Model)
            1 * model.addAttribute("name", expectedName)

        expect:
            viewName == frontendController.hello(model, name)

        where:
            name        || expectedName | viewName
            'Miro'      || 'Miro'       | 'index'
            'mIRek'     || 'Mirek'      | 'index'
            'miroslaw'  || 'Miroslaw'   | 'index'
    }

}
