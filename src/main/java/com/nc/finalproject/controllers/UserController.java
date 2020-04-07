package com.nc.finalproject.controllers;

import com.nc.finalproject.controllers.constants.Errors;
import com.nc.finalproject.controllers.constants.LoggedIn;
import com.nc.finalproject.controllers.constants.URLs;
import com.nc.finalproject.controllers.constants.ViewNames;
import com.nc.finalproject.model.User;
import com.nc.finalproject.service.SecurityService;
import com.nc.finalproject.service.UserService;
import com.nc.finalproject.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for {@link com.nc.finalproject.model.User}'s pages.
 *
 * @author WildDed
 * @version 1.0
 */

@Controller
public class UserController {
    private static final String DOCTORS_FROM_DB = "doctors";

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = URLs.REGISTRATION)
    public ModelAndView registration() {
        return new ModelAndView(ViewNames.REGISTRATION)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PostMapping(value = URLs.REGISTRATION)
    public ModelAndView registration(@PathVariable @ModelAttribute("user") User user) {
        if (userValidator.validateUsername(user.getUsername()) != null
                || userValidator.validatePassword(user.getPassword()) != null
                || userValidator.validateEmail(user.getEmail()) != null
                || userValidator.validateConfirmPassword(user.getPassword(), user.getConfirmPassword()) != null) {
            return new ModelAndView(ViewNames.REGISTRATION)
                    .addObject(Errors.USERNAME, userValidator.validateUsername(user.getUsername()))
                    .addObject(Errors.EMAIL, userValidator.validateEmail(user.getEmail()))
                    .addObject(Errors.PASS, userValidator.validatePassword(user.getPassword()))
                    .addObject(Errors.CONFIRM_PASS, userValidator.validateConfirmPassword(user.getPassword(), user.getConfirmPassword()))
                    .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        }

        userService.saveAndFlush(user);
        securityService.autoLogin(user.getUsername(), passwordEncoder.encode(user.getPassword()));


        return new ModelAndView(ViewNames.HOME)
                .addObject(LoggedIn.USER, userService.findByUsername(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @GetMapping(value = URLs.LOGIN)
    public ModelAndView login() {
        return new ModelAndView(ViewNames.LOGIN)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

//    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_DOCTOR')")
    @GetMapping(value = URLs.PROFILE)
    public ModelAndView profile() {
        return new ModelAndView(ViewNames.PROFILE)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

//    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_DOCTOR')")
    @PostMapping(value = URLs.PROFILE)
    public ModelAndView profile(@PathVariable @ModelAttribute("user") User user,
                                @RequestParam(name = "oldPassword") String oldPassword) {
        if (userValidator.validateUsernameForUpdate(user.getUsername()) != null
                || userValidator.validateOldPassword(oldPassword) != null
                || userValidator.validatePasswordForUpdate(user.getPassword()) != null
                || userValidator.validateEmail(user.getEmail()) != null
                || userValidator.validateConfirmPassword(user.getPassword(), user.getConfirmPassword()) != null) {
            return new ModelAndView(ViewNames.PROFILE)
                    .addObject(Errors.USERNAME, userValidator.validateUsernameForUpdate(user.getUsername()))
                    .addObject(Errors.EMAIL, userValidator.validateEmail(user.getEmail()))
                    .addObject(Errors.PASS, userValidator.validatePasswordForUpdate(user.getPassword()))
                    .addObject(Errors.OLD_PASS, userValidator.validateOldPassword(oldPassword))
                    .addObject(Errors.CONFIRM_PASS, userValidator.validateConfirmPassword(user.getPassword(), user.getConfirmPassword()))
                    .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        }

        userService.saveAndFlush(user);
        securityService.autoLogin(user.getUsername(), passwordEncoder.encode(user.getPassword()));


        return new ModelAndView(ViewNames.PROFILE)
                .addObject("message", "Success")
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = URLs.CREATE_ACCOUNT)
    public ModelAndView createAccount() {
        return new ModelAndView(ViewNames.CREATE_ACCOUNT)
                .addObject("account", new User())
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = URLs.CREATE_ACCOUNT)
    public ModelAndView createAccount(@PathVariable @ModelAttribute("account") User user) {

        if (userValidator.validateUsername(user.getUsername()) != null
                || userValidator.validatePassword(user.getPassword()) != null
                || userValidator.validateEmail(user.getEmail()) != null) {
            return new ModelAndView(ViewNames.CREATE_ACCOUNT)
                    .addObject(Errors.USERNAME, userValidator.validateUsername(user.getUsername()))
                    .addObject(Errors.EMAIL, userValidator.validateEmail(user.getEmail()))
                    .addObject(Errors.PASS, userValidator.validatePassword(user.getPassword()))
                    .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        }

        userService.saveAndFlushDoctor(user);

        return new ModelAndView(ViewNames.CREATE_ACCOUNT)
                .addObject("message", "Success")
                .addObject("account", new User())
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = URLs.DOCTORS)
    public ModelAndView doctors() {
        return new ModelAndView(ViewNames.DOCTORS)
                .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = URLs.DOCTORS)
    public ModelAndView doctors(@PathVariable @ModelAttribute("doctor") User doctor) {
        if (userService.findById(doctor.getId()).isPresent()) {

            userService.deleteById(doctor.getId());

            return new ModelAndView(ViewNames.DOCTORS)
                    .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                    .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        } else {
            return new ModelAndView(ViewNames.DOCTORS)
                    .addObject("error", "error")
                    .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                    .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        }
    }
}
