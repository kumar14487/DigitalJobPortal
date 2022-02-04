package com.dc.job.portal.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleTypeValidator implements ConstraintValidator<RoleTypeValidation, String>
{
    public boolean isValid(String colorName, ConstraintValidatorContext cxt) {
        List list = Arrays.asList(new String[]{"RECRUITER","JOBSEEKER"});
        return list.contains(colorName);
    }
}