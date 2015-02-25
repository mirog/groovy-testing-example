package jug.module

import geb.Module

class MenuBarModule extends Module {
    static content = {
        loginLink {
            $('ul > li:nth-child(1) > a')
        }
        mainPageLink {
            $('ul > li:nth-child(2) > a')
        }
        weatherLink {
            $('ul > li:nth-child(3) > a')
        }
        logout {
            $('ul > li:nth-child(4) > a')
        }
    }
}
