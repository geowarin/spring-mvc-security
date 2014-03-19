package com.geowarin.mvc.config.security;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * This initializer needs to be loaded after spring application context is loaded for the delegating proxy filter to
 * work. Hence, the @Order(2).
 *
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
@Order(2)
public class WebSecurityInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", delegatingFilterProxy);
		securityFilter.setAsyncSupported(true);
		securityFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC), false, "/*");
	}
}
