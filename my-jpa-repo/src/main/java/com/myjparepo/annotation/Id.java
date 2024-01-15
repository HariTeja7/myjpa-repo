package main.java.com.myjparepo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The field annotated with id is referred as primary key in database entity
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {

	public long start() default 1;

	public long minValue() default 1;

	public long maxValue() default Long.MAX_VALUE;

	public long increment() default 1;

}
