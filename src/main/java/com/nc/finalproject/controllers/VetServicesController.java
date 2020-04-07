package com.nc.finalproject.controllers;

import com.nc.finalproject.controllers.constants.*;
import com.nc.finalproject.model.User;
import com.nc.finalproject.model.VetService;
import com.nc.finalproject.model.VetServiceOfUser;
import com.nc.finalproject.service.*;
import com.nc.finalproject.validator.VetServiceOfUserValidator;
import com.nc.finalproject.validator.VetServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller for operations with entity {@link com.nc.finalproject.model.VetService}.
 *
 * @author WildDed
 * @version 1.0
 */

@Controller
public class VetServicesController {
    private static final String SERVICES_FROM_DB = "services";
    private static final String DOCTORS_FROM_DB = "doctors";
    private static final String PETS_FROM_DB = "pets";

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private VetServiceService vetServiceService;

    @Autowired
    private VetServiceOfUserService vetServiceOfUserService;

    @Autowired
    private VetServiceValidator vetServiceValidator;

    @Autowired
    private VetServiceOfUserValidator vetServiceOfUserValidator;

    @Autowired
    private PetService petService;

    @GetMapping(URLs.SERVICES)
    public ModelAndView services() {
        ModelAndView modelAndView = new ModelAndView(ViewNames.SERVICES)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));

        if (securityService.findLoggedInUsername().equals("anonymousUser")
                || userService.findRoleByUsername(securityService.findLoggedInUsername()).equals(Roles.ROLE_DOCTOR)) {
            return modelAndView.addObject(SERVICES_FROM_DB, vetServiceService.findAll());

        } else if (userService.findRoleByUsername(securityService.findLoggedInUsername()).equals(Roles.ROLE_USER)) {
            return modelAndView
                    .addObject(SERVICES_FROM_DB, vetServiceService.findAll())
                    .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                    .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()));

        } else if (userService.findRoleByUsername(securityService.findLoggedInUsername()).equals(Roles.ROLE_ADMIN)) {
            return modelAndView.addObject(SERVICES_FROM_DB, vetServiceService.findAll());

        } else return new ModelAndView(ViewNames.HOME)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping(URLs.SERVICES)
    public ModelAndView services(@PathVariable @ModelAttribute("vetService") VetService vetService,
                                 @RequestParam(name = "btnProcedure") String btnProcedure,
                                 @RequestParam(name = "date", required = false) String date,
                                 @RequestParam(name = "petName", required = false) String petName,
                                 @RequestParam(name = "doctorName", required = false) String doctorName,
                                 BindingResult bindingResult) {
        if (btnProcedure.equals("Create")) {

            vetServiceValidator.validate(vetService, bindingResult);

            if (bindingResult.hasErrors()) {
                return new ModelAndView(ViewNames.SERVICES)
                        .addObject("errors", bindingResult.getAllErrors())
                        .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
            }
            vetServiceService.saveAndFlush(vetService);
        }

        if (btnProcedure.equals("Update")) {

            vetServiceValidator.validateForUpdate(vetService, bindingResult);

            if (bindingResult.hasErrors()) {
                return new ModelAndView(ViewNames.SERVICES)
                        .addObject("errors", bindingResult.getAllErrors())
                        .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
            }
            vetServiceService.saveAndFlush(vetService);
        }

        if (btnProcedure.equals("Delete")) {
            vetServiceService.deleteById(vetService.getId());
        }

        if (btnProcedure.equals("Order")) {
            if (vetServiceOfUserValidator.validateDoctorNameForUserService(doctorName) != null
                    || vetServiceOfUserValidator.validateDateForUserService(date, doctorName) != null
                    || vetServiceOfUserValidator.validatePetNameForUSerService(securityService.findLoggedInUsername(), petName) != null) {
                return new ModelAndView(ViewNames.SERVICES)
                        .addObject(Errors.SERVICE_NAME, vetService.getName())
                        .addObject(Errors.DOCTOR_NAME, vetServiceOfUserValidator.validateDoctorNameForUserService(doctorName))
                        .addObject(Errors.DATE, vetServiceOfUserValidator.validateDateForUserService(date, doctorName))
                        .addObject(Errors.PET_NAME, vetServiceOfUserValidator.
                                validatePetNameForUSerService(securityService.findLoggedInUsername(), petName))
                        .addObject(SERVICES_FROM_DB, vetServiceService.findAll())
                        .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                        .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
            }
            vetServiceOfUserService.saveAndFlush(new VetServiceOfUser(vetService, date,
                    userService.findByUsername(securityService.findLoggedInUsername()), petService.findByName(petName),
                    userService.findByUsername(doctorName)));
        }

        return new ModelAndView(ViewNames.SERVICES)
                .addObject(SERVICES_FROM_DB, vetServiceService.findAll())
                .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_DOCTOR')")
    @GetMapping(URLs.ORDERED_SERVICES)
    public ModelAndView userServices() {
        if (userService.findRoleByUsername(securityService.findLoggedInUsername()).equals(Roles.ROLE_DOCTOR)) {
            return new ModelAndView(ViewNames.ORDERED_SERVICES)
                    .addObject(SERVICES_FROM_DB, vetServiceOfUserService.findAllByNameDoctorAndDate(
                            userService.findRoleByUsername(securityService.findLoggedInUsername())
                                    .equals(Roles.ROLE_DOCTOR) ? securityService.findLoggedInUsername() : null,
                            new SimpleDateFormat("yyyy/MM/dd").format(new Date())))
                    .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.USER,new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        } else {
            return new ModelAndView(ViewNames.ORDERED_SERVICES)
                    .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                    .addObject(SERVICES_FROM_DB, vetServiceOfUserService.findAllById(userService.
                            findByUsername(securityService.findLoggedInUsername()).getId()))
                    .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_DOCTOR')")
    @PostMapping(URLs.ORDERED_SERVICES)
    public ModelAndView userServices(@PathVariable @ModelAttribute("vetServiceOfUser") VetServiceOfUser vetServiceOfUser,
                                     @RequestParam(name = "btnProcedure") String btnProcedure,
                                     @RequestParam(name = "petName", required = false) String petName,
                                     @RequestParam(name = "date", required = false) String date) {
        vetServiceOfUser.setPet(petService.findByName(petName));
        if (btnProcedure.equals("Relocate")) {
            if (vetServiceOfUserValidator.validateDoctorNameForUserService(vetServiceOfUser.getDoctor().getUsername()) != null
                    || vetServiceOfUserValidator.validateDateForUserService(vetServiceOfUser.getDate(), vetServiceOfUser.getDoctor().getUsername()) != null
                    || vetServiceOfUserValidator.validatePetNameForUSerService(securityService.findLoggedInUsername(), petName) != null) {

                return new ModelAndView(ViewNames.ORDERED_SERVICES)
                        .addObject(Errors.SERVICE_ID, vetServiceOfUser.getId())
                        .addObject(Errors.DOCTOR_NAME, vetServiceOfUserValidator.
                                validateDoctorNameForUserService(vetServiceOfUser.getDoctor().getUsername()))
                        .addObject(Errors.DATE, vetServiceOfUserValidator.
                                validateDateForUserService(vetServiceOfUser.getDate(), vetServiceOfUser.getDoctor().getUsername()))
                        .addObject(Errors.PET_NAME, vetServiceOfUserValidator.
                                validatePetNameForUSerService(securityService.findLoggedInUsername(), petName))
                        .addObject(SERVICES_FROM_DB, vetServiceOfUserService.findAllById(userService.
                                findByUsername(securityService.findLoggedInUsername()).getId()))
                        .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                        .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
            }

            vetServiceOfUserService.saveAndFlush(vetServiceOfUser);
        }

        if (btnProcedure.equals("Refuse")) {
            vetServiceOfUserService.deleteById(vetServiceOfUser.getId());
        }

        if (btnProcedure.equals("Find")) {
            return new ModelAndView(ViewNames.ORDERED_SERVICES)
                    .addObject(SERVICES_FROM_DB, vetServiceOfUserService.findAllByNameDoctorAndDate(
                            userService.findRoleByUsername(securityService.findLoggedInUsername())
                                    .equals(Roles.ROLE_DOCTOR) ? securityService.findLoggedInUsername() : null, date))
                    .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                    .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
        }

        return new ModelAndView(ViewNames.ORDERED_SERVICES)
                .addObject(DOCTORS_FROM_DB, userService.findDoctors())
                .addObject(SERVICES_FROM_DB, vetServiceOfUserService.findAllById(userService.
                        findByUsername(securityService.findLoggedInUsername()).getId()))
                .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }
}
