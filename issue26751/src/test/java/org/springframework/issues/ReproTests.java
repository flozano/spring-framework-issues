package org.springframework.issues;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.env.MockPropertySource;

import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit.SingleInstancePostgresRule;

/**
 * Unit test that reproduces an issue reported against SPR JIRA. @Test methods
 * within need not pass with the green bar! Rather they should fail in such a
 * way that demonstrates the reported issue.
 */
public class ReproTests {
	@ClassRule
	public static SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();
	static AnnotationConfigApplicationContext ctx;
	static MyService service;

	private static MockPropertySource setupDB() {
		var ps = new MockPropertySource();
		var jdbc = new JdbcTemplate(pg.getEmbeddedPostgres().getPostgresDatabase());
		jdbc.batchUpdate("CREATE DATABASE db1", "CREATE DATABASE db2");
		ps.setProperty("db1.url", pg.getEmbeddedPostgres().getJdbcUrl("postgres", "db1"));
		ps.setProperty("db1.username", "postgres");
		ps.setProperty("db1.password", "");
		ps.setProperty("db2.url", pg.getEmbeddedPostgres().getJdbcUrl("postgres", "db2"));
		ps.setProperty("db2.username", "postgres");
		ps.setProperty("db2.password", "");
		return ps;
	}

	@BeforeClass
	public static void setUp() {
		ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().getPropertySources().addLast(setupDB());
		ctx.scan("org.springframework.issues");
		ctx.refresh();
		ctx.start();
		service = ctx.getBean(MyService.class);
	}

	@AfterClass
	public static void tearDown() {
		service = null;
		ctx.close();
		ctx = null;
	}

	@Test
	public void publishThenOther() {
		service.doPublishThenOther();
	}

	@Test
	public void otherThenPublish() {
		service.doOtherThenPublish();
	}

	@Test
	public void otherThenPublishThenOther() {
		service.doOtherPublishThenOther();
	}

	@Test
	public void publishThenOtherThenPublish() {
		service.doPublishThenOtherThenPublish();
	}

	//

	@Test
	public void publishThenOtherFail() {
		service.doPublishThenOtherFail();
	}

	@Test
	public void otherFailThenPublish() {
		service.doOtherFailPublishThenOther();
	}

	@Test
	public void otherFailThenPublishThenOther() {
		service.doOtherFailPublishThenOther();
	}

	@Test
	public void publishThenOtherFailThenPublish() {
		service.doPublishThenOtherFailThenPublish();
	}

}
