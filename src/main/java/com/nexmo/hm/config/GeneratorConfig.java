package com.nexmo.hm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nexmo.hm.utils.RandomWordsGenerator;

/**
 * @author Sunny Ghosh
 *
 */

@Configuration
@ComponentScan(basePackages = "com.nexmo.hm.utils")
class GeneratorConfig {
	
	@Bean
	public RandomWordsGenerator getRandonWordsGenerator() {
		return new RandomWordsGenerator();
	}
}