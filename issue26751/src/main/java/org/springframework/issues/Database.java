package org.springframework.issues;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
class Database {

	@Bean
	@Primary
	DataSource dataSource() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		ds.setUser("sa");
		ds.setPassword("sa");
		return ds;
	}

	@Bean
	@Primary
	PlatformTransactionManager txManager() {
		return new JdbcTransactionManager(dataSource());
	}

	@Bean
	@Primary
	JdbcOperations jdbcOps() {
		return new JdbcTemplate(dataSource());
	}
}
