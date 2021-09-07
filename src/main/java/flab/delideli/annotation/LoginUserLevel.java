package flab.delideli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginUserLevel {

	UserLevel role();

	enum UserLevel {
		ADMIN_LEVEL,
		OWNER_LEVEL,
		MEMBER_LEVEL
	}

}
