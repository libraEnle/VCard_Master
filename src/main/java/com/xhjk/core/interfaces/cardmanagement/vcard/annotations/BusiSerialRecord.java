package com.xhjk.core.interfaces.cardmanagement.vcard.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解类，可引入日志操作等非业务操作类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface BusiSerialRecord {

    public String busCode();
    public String desc() default "";

}
