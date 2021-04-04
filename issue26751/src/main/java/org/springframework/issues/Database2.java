package org.springframework.issues;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
class Database2 {

	@Bean
	@Secondary
	DataSource dataSource2() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setUrl("jdbc:h2:mem:db2;DB_CLOSE_DELAY=-1");
		ds.setUser("sa");
		ds.setPassword("sa");
		return ds;
	}

	@Bean
	@Secondary
	PlatformTransactionManager txManager2() {
		return new JdbcTransactionManager(dataSource2());
	}

	@Bean
	@Secondary
	JdbcOperations jdbcOps2() {
		return new JdbcTemplate(dataSource2());
	}
}
