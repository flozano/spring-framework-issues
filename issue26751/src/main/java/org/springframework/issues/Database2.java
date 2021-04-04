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
class Database2 {

	@Value("${db2.url}")
	String url;
	@Value("${db2.username}")
	String username;
	@Value("${db2.password}")
	String password;

	@Bean
	@Primary
	DataSource dataSource2() {
		var pg = new PGSimpleDataSource();
		pg.setUrl(url);
		pg.setUser(username);
		pg.setPassword(password);
		HikariDataSource ds = new HikariDataSource();
		ds.setDataSource(pg);
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
