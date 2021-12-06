package cz.mendel.uni.controllers.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice("cz.mendel.uni.controllers")
public class ExceptionHandlerController {
    private static final String ERROR_VIEW = "errors/error";

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericExceptions(Exception exception) {
        log.debug("Start handling generic exception - \"{}\"", exception.getMessage());
        log.warn(exception.getMessage(), exception);
        ModelAndView modelAndView = new ModelAndView(ERROR_VIEW);
        return modelAndView.addObject("exception", exception.getClass().getSimpleName());
    }
}
