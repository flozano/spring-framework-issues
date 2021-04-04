package org.springframework.issues;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
class MainConfiguration {
	@Bean
	static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
