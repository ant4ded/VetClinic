package com.nc.finalproject.controllers.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Roles for controllers classes.
 *
 * @author WildDed
 * @version 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Roles {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_DOCTOR = "ROLE_DOCTOR";
}
