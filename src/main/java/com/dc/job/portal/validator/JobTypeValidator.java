package com.dc.job.portal.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JobTypeValidator implements ConstraintValidator<JobTypeValidation, String>
{
    public boolean isValid(String colorName, ConstraintValidatorContext cxt) {
        List list = Arrays.asList(new String[]{"FULLTIME","PARTTIME","CONTRACT"});
        return list.contains(colorName);
    }
}