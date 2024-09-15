package com.example.springboottabelogkadai.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
	String message() default "電話番号の形式が正しくありません。";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}