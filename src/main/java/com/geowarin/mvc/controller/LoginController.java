package com.geowarin.mvc.controller;

import java.io.IOException;
import java.security.Principal;

import javax.naming.AuthenticationException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geowarin.mvc.dto.LoginForm;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class is an extract from the spring-security sample 'sevlet-api'.
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
@Controller
public class LoginController {
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * Demonstrates that {@link HttpServletRequest#authenticate(HttpServletResponse)} will send the user to the log in
     * page configured within Spring Security if the user is not already authenticated.
     */
    @RequestMapping("/authenticate")
    public String authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticate = request.authenticate(response);
        return authenticate ? "index" : null;
    }

    /**
     * Authentication with Spring Security using {@link HttpServletRequest#login(String, String)}.
     *
     * <p>
     * If we fail to authenticate, a {@link ServletException} is thrown that wraps the original
     * {@link AuthenticationException} from Spring Security. This means we can catch the {@link ServletException} to
     * display the error message. Alternatively, we could allow the {@link ServletException} to propagate and Spring
     * Security's {@link ExceptionTranslationFilter} would catch it and process it appropriately.
     * </p>
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute LoginForm loginForm,
                        BindingResult result) throws ServletException {
        try {
            request.login(loginForm.getUsername(), loginForm.getPassword());
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                return "redirect:" + savedRequest.getRedirectUrl();
            } else {
                return "redirect:/";
            }

        } catch (ServletException authenticationFailed) {
            result.rejectValue(null, "authentication.failed");
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute LoginForm loginForm) {
        return "login";
    }

    /**
     * Demonstrates that invoking {@link HttpServletRequest#logout()} will log the user out.
     * We then redirect the user to the home page.
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
    	request.logout();
        return "redirect:/";
    }
}