package com.nc.finalproject.validator;

import com.nc.finalproject.service.PetService;
import com.nc.finalproject.validator.constants.PetValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator for operations with entity {@link com.nc.finalproject.model.Pet}
 *
 * @author WildDed
 * @version 1.0
 */

@Component
public class PetValidator {
    private static final int LENGTH_MIN = 3;
    private static final int LENGTH_MAX = 32;

    @Autowired
    private PetService petService;

    public String validateName(String name) {
        if (validateRequiredField(name) == null) {
            if (name.length() < LENGTH_MIN || name.length() > LENGTH_MAX) {
                return PetValidatorMessage.PET_NAME_LENGTH;
            }
            if (!Character.isUpperCase(name.charAt(0))) {
                return PetValidatorMessage.CAPITAL_LETTER;
            }
            if (petService.findByName(name) != null && name.equals(petService.findByName(name).getOwner().getUsername())) {
                return PetValidatorMessage.PET_NAME_EXIST;
            }
        }
        return validateRequiredField(name);
    }

    public String validateNameForUpdate(String name) {
        if (validateRequiredField(name) == null) {
            if (name.length() < LENGTH_MIN || name.length() > LENGTH_MAX) {
                return PetValidatorMessage.PET_NAME_LENGTH;
            }
            if (!Character.isUpperCase(name.charAt(0))) {
                return PetValidatorMessage.CAPITAL_LETTER;
            }
        }
        return validateRequiredField(name);
    }

    public String validateKind(String kind) {
        if (validateRequiredField(kind) == null && !Character.isUpperCase(kind.charAt(0))) {
            return PetValidatorMessage.CAPITAL_LETTER;
        }
        return validateRequiredField(kind);
    }

    private String validateRequiredField(String field) {
        if (field == null || field.equals("")) {
            return PetValidatorMessage.REQUIRED_FIELD;
        } else {
            return null;
        }
    }
}
