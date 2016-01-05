package com.nexmo.hm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nexmo.hm.management.SessionManagement;

/**
 * @author Sunny Ghosh
 *
 */

@Configuration
@ComponentScan(basePackages = "com.nexmo.hm.management")
class SessionConfig {
	
	@Bean
	public SessionManagement getSessionManagement() {
		return new SessionManagement();
	}
}