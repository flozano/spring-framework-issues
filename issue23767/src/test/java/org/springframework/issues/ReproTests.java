package org.springframework.issues;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test that reproduces an issue reported against SPR JIRA. @Test methods within
 * need not pass with the green bar! Rather they should fail in such a way that
 * demonstrates the reported issue.
 */
public class ReproTests {

	@Test
	public void repro() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
		ctx.start();
		ctx.getBean(MyService.class).myMethod();
	}

}
