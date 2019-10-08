package org.springframework.issues;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MyDAO {
	private final JdbcTemplate jdbc;

	public MyDAO(@Autowired @Qualifier("dataSource2") DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	@Transactional(value = "anotherTransactionManager", propagation = Propagation.MANDATORY)
	public List<?> findAll() {
		jdbc.queryForObject("SELECT 1 FROM INFORMATION_SCHEMA.SYSTEM_USERS", Integer.class);
		return Collections.emptyList();
	}
}
