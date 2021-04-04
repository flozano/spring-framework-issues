package org.springframework.issues;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test that reproduces an issue reported against SPR JIRA. @Test methods
 * within need not pass with the green bar! Rather they should fail in such a
 * way that demonstrates the reported issue.
 */
public class ReproTests {

	static AnnotationConfigApplicationContext ctx;
	static MyService service;

	@BeforeClass
	public static void setUp() {
		ctx = new AnnotationConfigApplicationContext("org.springframework.issues");
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
