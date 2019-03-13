package com.example.eureka_client.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/11下午9:18
 */
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultTranscantional {
    public String index() default "";

}
