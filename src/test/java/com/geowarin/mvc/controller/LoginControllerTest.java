package com.geowarin.mvc.controller;

import com.geowarin.mvc.config.security.SecurityConfig;
import com.geowarin.mvc.config.service.ServiceConfig;
import com.geowarin.mvc.config.web.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, ServiceConfig.class, SecurityConfig.class})
public class LoginControllerTest {

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }

    @Test
    public void postEmptyData() throws Exception {
        this.mockMvc.perform(post("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
    }

    @Test
    public void postWrongLogin() throws Exception {
        this.mockMvc.perform(post("/login").param("username", "unexisting").param("password", "user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
    }

    // example from
    // https://github.com/SpringSource/spring-test-mvc/blob/master/src/test/java/org/springframework/test/web/server/samples/context/SpringSecurityTests.java
    @Test
    public void userAuthenticates() throws Exception {
        final String username = "user";
        mockMvc.perform(post("/login").param("username", username).param("password", "user"))
                .andExpect(redirectedUrl("/"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        SecurityContext securityContext = (SecurityContext) session.
                                getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
                        assertEquals(securityContext.getAuthentication().getName(), username);
                    }
                });
    }
}
