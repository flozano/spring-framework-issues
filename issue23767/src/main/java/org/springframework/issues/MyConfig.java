package org.springframework.issues;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
@ComponentScan("org.springframework.issues")
@EnableTransactionManagement
public class MyConfig {

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setName("db1").build();
	}

	@Bean
	PlatformTransactionManager defaultTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	DataSource dataSource2() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setName("db2").build();
	}

	@Bean
	PlatformTransactionManager anotherTransactionManager() {
		return new DataSourceTransactionManager(dataSource2());
	}

	@Bean
	@Primary
	TransactionInterceptor transactionInterceptor() {
		TransactionInterceptor interceptor = new TransactionInterceptor();
		interceptor.setTransactionAttributeSource(transactionAttributeSource());
		return interceptor;
	}

	@Bean
	TransactionAttributeSource transactionAttributeSource() {
		return new AnnotationTransactionAttributeSource();
	}
}
