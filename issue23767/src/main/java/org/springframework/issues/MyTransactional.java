package org.springframework.issues;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTransactional {

	@AliasFor(annotation = Transactional.class, attribute = "value")
	String value() default "defaultTransactionManager";

	@AliasFor(annotation = Transactional.class, attribute = "isolation")
	Isolation isolation() default Isolation.DEFAULT;

}
