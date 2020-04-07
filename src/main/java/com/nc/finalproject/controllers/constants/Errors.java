package com.nc.finalproject.controllers.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Errors message for controllers classes.
 *
 * @author WildDed
 * @version 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Errors {
    public static final String USERNAME = "errorUsername";
    public static final String EMAIL = "errorEmail";
    public static final String PASS = "errorPassword";
    public static final String CONFIRM_PASS = "errorConfirmPassword";
    public static final String OLD_PASS = "errorOldPassword";

    public static final String PET_ID = "errorPetId";
    public static final String PET_NAME = "errorPetName";
    public static final String PET_KIND = "errorKind";

    public static final String SERVICE_ID = "errorServiceId";
    public static final String SERVICE_NAME = "errorServiceName";
    public static final String DOCTOR_NAME = "errorDoctorName";
    public static final String DATE = "errorDate";
}
