package com.wgjc.login.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Description:类和方法添加登录权限验证
 * @author hc
 * @da2019年7月28日
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Login {
	String desc() default "验证是否登录";
}
