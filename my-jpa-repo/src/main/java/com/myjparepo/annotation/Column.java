package main.java.com.myjparepo.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Field of entity class annotated with column can have custom column name and other attributes
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {

	public String name() default "";

	public int size() default 30;

	public boolean nullable() default true;

	public boolean unique() default false;

}