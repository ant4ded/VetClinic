package com.nc.finalproject.validator;

import com.nc.finalproject.model.Pet;
import com.nc.finalproject.model.User;
import com.nc.finalproject.service.PetService;
import com.nc.finalproject.service.UserService;
import com.nc.finalproject.service.VetServiceOfUserService;
import com.nc.finalproject.validator.constants.VetServicesOfUserValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Validator for {@link com.nc.finalproject.model.VetServiceOfUser} class,
 *
 * @author WildDed
 * @version 1.0
 */

@Component
public class VetServiceOfUserValidator {

    @Autowired
    PetService petService;
    @Autowired
    private VetServiceOfUserService vetServiceOfUserService;

    @Autowired
    private UserService userService;

    public String validateDateForUserService(String date, String doctor) {
        String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        try {
            if (Integer.parseInt(date.substring(0, 4)) < Integer.parseInt(currentDate.substring(0, 4))
                    || (Integer.parseInt(date.substring(0, 4)) >= Integer.parseInt(currentDate.substring(0, 4))
                    && Integer.parseInt(date.substring(5, 7)) < Integer.parseInt(currentDate.substring(5, 7)))
                    || (Integer.parseInt(date.substring(5, 7)) >= Integer.parseInt(currentDate.substring(5, 7))
                    && Integer.parseInt(date.substring(8)) <= Integer.parseInt(currentDate.substring(8)))) {
                return VetServicesOfUserValidatorMessage.LATE_DATE;
            }
            if (vetServiceOfUserService.findAllByNameDoctorAndDate(doctor, date).size() >= 8) {
                return VetServicesOfUserValidatorMessage.MANY_OPERATIONS;
            }
        } catch (NumberFormatException e) {
            return VetServicesOfUserValidatorMessage.INCORRECT_DATE;
        }
        return null;
    }

    public String validateDoctorNameForUserService(String doctorName) {
        boolean requiredFlag = false;
        List<User> doctors = userService.findDoctors();
        for (User doctor : doctors) {
            if (doctor.getUsername().equals(doctorName)) {
                requiredFlag = true;
            }
        }
        if (!requiredFlag) {
            return VetServicesOfUserValidatorMessage.DONT_HAVE_DOCTOR;
        } else {
            return null;
        }
    }

    public String validatePetNameForUSerService(String username, String petName) {
        boolean requiredFlag = false;
        List<Pet> pets = petService.findAllByUsername(username);
        for (Pet pet : pets) {
            if (pet.getName().equals(petName)) {
                requiredFlag = true;
            }
        }
        if (!requiredFlag) {
            return VetServicesOfUserValidatorMessage.DONT_HAVE_PET;
        } else {
            return null;
        }
    }
}
