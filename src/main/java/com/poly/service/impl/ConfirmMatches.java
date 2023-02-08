// package com.poly.service.impl;

// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// import javax.validation.ConstraintValidator;
// import javax.validation.ConstraintValidatorContext;

// import com.poly.DTO.RegisterDataForm;
// import com.poly.service.ConfirmValid;

// public class ConfirmMatches 
//   implements ConstraintValidator<ConfirmValid, Object> {
    
//     @Override
//     public void initialize(ConfirmValid constraintAnnotation) {       
//     }
//     @Override
//     public boolean isValid(Object obj, ConstraintValidatorContext context){   
//         RegisterDataForm user = (RegisterDataForm) obj;
//         return user.getPassword().equals(user.getConfirm());    
//     } 
   
    
// }