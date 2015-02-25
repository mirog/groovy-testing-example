package jug.page

import geb.Page
import jug.module.MenuBarModule

class WeatherPage extends Page {
    static url = '/weather'

    static at = { title == 'Weather page'  }

    static content = {
        menuBar { module MenuBarModule, $('#menu')}
        mainTitle(required: false) { $('h1')}
    }
}
