package com.nc.finalproject.validator;

import com.nc.finalproject.service.SecurityService;
import com.nc.finalproject.service.UserService;
import com.nc.finalproject.validator.constants.UserValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

/**
 * Validator for {@link com.nc.finalproject.model.User} class.
 *
 * @author WildDed
 * @version 1.0s
 */

@Component
public class UserValidator {
    private static final int LENGTH_MIN = 8;
    private static final int LENGTH_MAX = 32;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String validateEmail(String email) {
        return validateRequiredField(email);
    }

    public String validateUsername(String username) {
        if (validateRequiredField(username) == null) {
            if (username.length() < LENGTH_MIN || username.length() > LENGTH_MAX) {
                return UserValidatorMessage.USERNAME_LENGTH;
            }
            if (userService.findByUsername(username) != null
                    && username.equals(userService.findByUsername(username).getUsername())) {
                return UserValidatorMessage.USERNAME_EXIST;

            }
        }
        return validateRequiredField(username);
    }

    public String validateUsernameForUpdate(String username) {
        if (validateRequiredField(username) == null
                && (username.length() < LENGTH_MIN || username.length() > LENGTH_MAX)) {
            return UserValidatorMessage.USERNAME_LENGTH;
        }
        return validateRequiredField(username);
    }

    public String validateOldPassword(String oldPassword) {
        if (validateRequiredField(oldPassword) == null && !passwordEncoder.matches(oldPassword,
                userService.findByUsername(securityService.findLoggedInUsername()).getPassword())) {
            return UserValidatorMessage.CURRENT_PASS;
        }
        return validateRequiredField(oldPassword);
    }

    public String validatePassword(String password) {
        if (validateRequiredField(password) == null && (password.length() < LENGTH_MIN || password.length() > LENGTH_MAX)) {
            return UserValidatorMessage.PASS_LENGTH;
        }
        return validateRequiredField(password);
    }

    public String validatePasswordForUpdate(String password) {
        if (validateRequiredField(password) == null) {
            if (password.length() < LENGTH_MIN || password.length() > LENGTH_MAX) {
                return UserValidatorMessage.PASS_LENGTH;
            }
            if (passwordEncoder.matches(password,
                    userService.findByUsername(securityService.findLoggedInUsername()).getPassword())) {
                return UserValidatorMessage.NEW_PASS_MATCH_OLD;
            }
        }
        return validateRequiredField(password);
    }

    public String validateConfirmPassword(String password, String confirmPassword) {
        if (validateRequiredField(confirmPassword) == null && !confirmPassword.equals(password)) {
            return UserValidatorMessage.PASS_DONT_MATCH;
        }
        return validateRequiredField(confirmPassword);
    }

    private String validateRequiredField(String field) {
        if (field == null || field.equals("")) {
            return UserValidatorMessage.REQUIRED_FIELD;
        } else {
            return null;
        }
    }
}
