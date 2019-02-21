package cn.lsu.chicken.room.myAnnotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneValidate.class})
public @interface Phone {
    String message() default "手机格式不正确";

    String regexp() default "";

    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};
}
