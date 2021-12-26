package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.servlet.ModelAndView
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
class ExceptionHandlerControllerTest extends Specification {
    private ExceptionHandlerController exceptionHandlerController = new ExceptionHandlerController()
    private ArrayIndexOutOfBoundsException exception = new ArrayIndexOutOfBoundsException()
    private ModelAndView expectedModelAndView = new ModelAndView("errors/error", "exception", exception.getClass().getSimpleName())

    def "modelAndView returned with errors/error view and model with exception simple name"() {
        given:
        def givenModelAndView = exceptionHandlerController.handleGenericExceptions(exception)
        expect:
        givenModelAndView.view == expectedModelAndView.view
        givenModelAndView.model == expectedModelAndView.model
    }
}
