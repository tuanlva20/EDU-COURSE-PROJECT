// package com.poly.service;

// import java.lang.annotation.Documented;
// import java.lang.annotation.ElementType;
// import java.lang.annotation.Retention;
// import java.lang.annotation.RetentionPolicy;
// import java.lang.annotation.Target;

// import javax.validation.Constraint;
// import javax.validation.Payload;

// import com.poly.service.impl.ConfirmMatches;

// @Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) 
// @Retention(RetentionPolicy.RUNTIME)
// @Constraint(validatedBy = ConfirmMatches.class)
// @Documented
// public @interface ConfirmValid {
//     String message() default "Nhập lại mật khẩu không chính xác";
//     Class<?>[] groups() default {}; 
//     Class<? extends Payload>[] payload() default {};
// }
