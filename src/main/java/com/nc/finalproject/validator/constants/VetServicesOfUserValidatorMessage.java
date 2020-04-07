package com.nc.finalproject.validator.constants;

/**
 * ValidatorMessage for {@link com.nc.finalproject.validator.VetServiceOfUserValidator} class.
 *
 * @author WildDed
 * @version 1.0
 */

public class VetServicesOfUserValidatorMessage extends ValidatorMessage {
    public static final String LATE_DATE = "It's too late to choose this date.";
    public static final String MANY_OPERATIONS = "It's too many operations for this doctor on this date.";
    public static final String INCORRECT_DATE = "Incorrect date.";
    public static final String DONT_HAVE_DOCTOR = "We don't have a doctor like that.";
    public static final String DONT_HAVE_PET = "You don't have a pet like that.";

    private VetServicesOfUserValidatorMessage() {
    }
}
