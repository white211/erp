package com.naswork.erp.utils.reflect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface anno {

    public  String value() default  "";

}
