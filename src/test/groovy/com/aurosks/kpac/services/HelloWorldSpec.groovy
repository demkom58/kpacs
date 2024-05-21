package com.aurosks.kpac.services

import spock.lang.Specification

class HelloWorldSpec extends Specification {

    class HelloWorld {
        String sayHello() {
            return "Hello World!"
        }
    }

    def "hello world"() {
        given:
        def helloWorld = new HelloWorld()

        expect:
        helloWorld.sayHello() == "Hello World!"
    }

}