
package com.geowarin.mvc.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@ImportResource("classpath:applicationContext-security.xml")
public class SecurityConfig {
	
	@Bean
	public AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/login");
	}
}
