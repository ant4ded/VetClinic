package com.nc.finalproject.validator.constants;

/**
 * ValidatorMessage for {@link com.nc.finalproject.validator.PetValidator} class.
 *
 * @author WildDed
 * @version 1.0
 */

public class PetValidatorMessage extends ValidatorMessage {
    public static final String PET_NAME_LENGTH = "Pet name must be between 3 and 32 letters.";
    public static final String CAPITAL_LETTER = "This field must begin with a capital letter.";
    public static final String PET_NAME_EXIST = "Such name of your pet already exists.";

    private PetValidatorMessage() {
    }
}
