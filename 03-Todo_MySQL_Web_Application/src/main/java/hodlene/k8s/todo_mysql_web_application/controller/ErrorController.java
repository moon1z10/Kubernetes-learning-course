package hodlene.k8s.todo_mysql_web_application.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", ex.getLocalizedMessage());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");

        return mav;
    }
}
