package com.demo.customanno;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {
    boolean value() default false;
}
