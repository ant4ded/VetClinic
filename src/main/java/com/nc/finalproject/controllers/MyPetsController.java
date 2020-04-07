package com.nc.finalproject.controllers;

import com.nc.finalproject.controllers.constants.Errors;
import com.nc.finalproject.controllers.constants.LoggedIn;
import com.nc.finalproject.controllers.constants.URLs;
import com.nc.finalproject.controllers.constants.ViewNames;
import com.nc.finalproject.model.Pet;
import com.nc.finalproject.model.User;
import com.nc.finalproject.service.PetService;
import com.nc.finalproject.service.SecurityService;
import com.nc.finalproject.service.UserService;
import com.nc.finalproject.validator.PetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for operations with entity {@link com.nc.finalproject.model.Pet}.
 *
 * @author WildDed
 * @version 1.0
 */

@Controller
public class MyPetsController {
    private static final String PETS_FROM_DB = "pets";

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PetService petService;

    @Autowired
    private PetValidator petValidator;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(URLs.MY_PETS)
    public ModelAndView myPets() {
        return new ModelAndView(ViewNames.MY_PETS)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(URLs.MY_PETS)
    public ModelAndView myPets(@PathVariable@ModelAttribute("pet") Pet pet,
                               @RequestParam("btnProcedure") String btnProcedure) {
        pet.setOwner(userService.findByUsername(securityService.findLoggedInUsername()));
        if (btnProcedure.equals("Create")) {
            if (petValidator.validateName(pet.getName()) != null
                    || petValidator.validateKind(pet.getPetType()) != null) {
                return new ModelAndView(ViewNames.MY_PETS)
                        .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                        .addObject(Errors.PET_ID, null)
                        .addObject(Errors.PET_NAME, petValidator.validateName(pet.getName()) )
                        .addObject(Errors.PET_KIND, petValidator.validateKind(pet.getPetType()))
                        .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
            }
            petService.saveAndFlush(pet);
        }
        if (btnProcedure.equals("Update")) {
            if (petValidator.validateNameForUpdate(pet.getName()) != null
                    || petValidator.validateKind(pet.getPetType()) != null) {
                return new ModelAndView(ViewNames.MY_PETS)
                        .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                        .addObject(Errors.PET_ID, pet.getId())
                        .addObject(Errors.PET_NAME, petValidator.validateNameForUpdate(pet.getName()))
                        .addObject(Errors.PET_KIND, petValidator.validateKind(pet.getPetType()))
                        .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                        .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
            }
            petService.saveAndFlush(pet);
        }
        if (btnProcedure.equals("Delete")) {
            petService.deleteById(pet.getId());
        }

        return new ModelAndView(ViewNames.MY_PETS)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(PETS_FROM_DB, petService.findAllByUsername(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }
}

