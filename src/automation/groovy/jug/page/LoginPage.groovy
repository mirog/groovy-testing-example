package jug.page

import geb.Page
import jug.module.MenuBarModule

class LoginPage extends Page {
    static url = '/login'

    static at = { title == 'Login page'  }

    static content = {
        menuBar { module MenuBarModule, $('#menu')}
        loginField { $("#username") }
        passwordField { $("#password") }
        submitButton { $("input[type='submit']")}
    }
}
