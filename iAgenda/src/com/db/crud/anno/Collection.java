package com.db.crud.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE)   
public @interface Collection {

	/**
	 * @return which collection shall this POJO be stored
	 */
	String name();
}
