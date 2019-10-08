package org.springframework.issues;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MyTransactional(value = "anotherTransactionManager")
@Retention(RetentionPolicy.RUNTIME)
public @interface AnotherMyTransactional {

}
