package com.demo.customanno;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value() default "null";
}
