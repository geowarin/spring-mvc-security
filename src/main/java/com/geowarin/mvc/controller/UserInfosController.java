package com.geowarin.mvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Date: 4/7/13
 * Time: 8:09 PM
 *
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@Controller
public class UserInfosController {

    @RequestMapping(value = "/userInfos", method = RequestMethod.GET)
    public String login() {
        return "userInfos";
    }

    /**
     * Demonstrates that Spring Security automatically populates {@link HttpServletRequest#getRemoteUser()} with the current username.
     */
    @ModelAttribute("remoteUser")
    public String remoteUser(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    /**
     * Demonstrates that Spring Security automatically populates {@link HttpServletRequest#getUserPrincipal()} with the
     * {@link Authentication} that is present on {@link SecurityContextHolder#getContext()}
     */
    @ModelAttribute("userPrincipal")
    public Principal userPrincipal(HttpServletRequest request) {
        return request.getUserPrincipal();
    }

    /**
     * Spring MVC will automatically resolve any object that implements {@link Principal} using {@link HttpServletRequest#getUserPrincipal()}.
     * Alternatively, you could also have an argument of type {@link Principal} which would not couple your controller to Spring Security.
     */
    @ModelAttribute
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }
}
