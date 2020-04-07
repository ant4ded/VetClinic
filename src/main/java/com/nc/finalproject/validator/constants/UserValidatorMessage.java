package com.nc.finalproject.validator.constants;

/**
 * ValidatorMessage for {@link com.nc.finalproject.validator.UserValidator} class.
 *
 * @author WildDed
 * @version 1.0
 */

public class UserValidatorMessage extends ValidatorMessage {
    public static final String USERNAME_LENGTH = "Username must be between 8 and 32 letters.";
    public static final String PASS_LENGTH = "Password must be between 8 and 32 letters.";
    public static final String USERNAME_EXIST = "Such username already exists.";
    public static final String CURRENT_PASS = "Incorrect password for this user.";
    public static final String NEW_PASS_MATCH_OLD = "The new password cannot match the old one.";
    public static final String PASS_DONT_MATCH = "Passwords don't match.";

    private UserValidatorMessage() {
    }
}
