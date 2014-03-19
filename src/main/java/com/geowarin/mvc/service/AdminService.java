package com.geowarin.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private static Logger log = LoggerFactory.getLogger(AdminService.class);
	
	
	// http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/access/expression/SecurityExpressionRoot.html
	// LoginUrlAuthenticationEntryPoint
	@PreAuthorize("isAuthenticated()")
	public void authenticatedAction() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Action triggered by user {}", authentication.getName());
	}
}
