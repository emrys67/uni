package cz.mendel.uni.controllers.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice("cz.mendel.uni.controllers")
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericExceptions(Exception ex){
        log.debug("Start handling generic exception - \"{}\"", ex.getMessage());
        log.warn(ex.getMessage(), ex);
        return prepareView( ex, "errors/error");
    }

    private ModelAndView prepareView(Exception exception, String view){
        log.debug("Start preparing view");
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("exception", exception.getClass().getSimpleName());
        log.debug("Start using view with name \"{}\"", exception.getClass().getSimpleName());
        return modelAndView;
    }
}
