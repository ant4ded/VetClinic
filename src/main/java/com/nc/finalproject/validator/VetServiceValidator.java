package com.nc.finalproject.validator;

import com.nc.finalproject.model.VetService;
import com.nc.finalproject.service.VetServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link com.nc.finalproject.model.VetService} class,
 * Implementation {@link Validator}.
 *
 * @author WildDed
 * @version 1.0
 */

@Component
public class VetServiceValidator implements Validator {

    @Autowired
    private VetServiceService vetServiceService;

    @Override
    public boolean supports(Class<?> aClass) {
        return VetService.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        validateForUpdate(o, errors);

        VetService vetService = (VetService) o;

        VetService vetServiceFromDataBase = vetServiceService.findByName(vetService.getName());

        if (vetServiceFromDataBase != null) {
            if (vetServiceFromDataBase.getName().equals(vetService.getName())) {
                errors.rejectValue("name", "Duplicate.vetServices.name");
            }
        }
    }

    public void validateForUpdate(Object o, Errors errors) {
        VetService vetService = (VetService) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "Required");


        if(!vetService.getName().equals("")) {
            if (!Character.isUpperCase(vetService.getName().charAt(0))) {
                errors.rejectValue("name", "Register.vetServices.name");
            }
        }
        if(vetService.getCost() != 0) {
            if (vetService.getCost() < 0) {
                errors.rejectValue("cost", "Unsigned.vetServices.cost");
            }
        }
    }
}
