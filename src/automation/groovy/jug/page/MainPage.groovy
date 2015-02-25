package jug.page

import geb.Page
import jug.module.MenuBarModule

class MainPage extends Page {
    static url = '/'

    static at = { title == 'Main page'  }

    static content = {
        mainText { $('h1') }
        menuBar { module MenuBarModule, $('#menu')}
    }
}
