package org.springframework.issues;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
class Database {
	@Value("${db1.url}")
	String url;
	@Value("${db1.username}")
	String username;
	@Value("${db1.password}")
	String password;

	@Bean
	@Primary
	DataSource dataSource() {
		var pg = new PGSimpleDataSource();
		pg.setUrl(url);
		pg.setUser(username);
		pg.setPassword(password);
		HikariDataSource ds = new HikariDataSource();
		ds.setDataSource(pg);
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
