package com.geowarin.mvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Date: 4/7/13
 * Time: 8:09 PM
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@Controller
public class AsyncController {

    /**
     * Demonstrates Spring Security with {@link javax.servlet.AsyncContext#start(Runnable)}. Spring Security will automatically
     * transfer the {@link org.springframework.security.core.context.SecurityContext} from the thread that {@link javax.servlet.AsyncContext#start(Runnable)} is invoked to the
     * new Thread that invokes the {@link Runnable}.
     */
    @RequestMapping("/async")
    public void asynch(HttpServletRequest request) {

        final AsyncContext async = request.startAsync();

        async.start(new Runnable() {
            public void run() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                try {
                    final HttpServletResponse asyncResponse = (HttpServletResponse) async.getResponse();
                    asyncResponse.setStatus(HttpServletResponse.SC_OK);
                    asyncResponse.getWriter().write(String.valueOf(authentication));
                    async.complete();

                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
