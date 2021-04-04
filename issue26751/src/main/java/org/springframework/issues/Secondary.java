package org.springframework.issues;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RUNTIME)
@Qualifier
public @interface Secondary {

}
