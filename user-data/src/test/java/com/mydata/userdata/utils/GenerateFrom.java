package com.mydata.userdata.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateFrom {

  /** Name of the parameter from which the new object need to be generated */
  String value() default "";

  /** List of fields to be skipped from the new object while copying parameter fields */
  String[] skipFields() default {};
}
