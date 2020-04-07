package com.nc.finalproject.controllers;

import com.nc.finalproject.controllers.constants.LoggedIn;
import com.nc.finalproject.controllers.constants.URLs;
import com.nc.finalproject.controllers.constants.ViewNames;
import com.nc.finalproject.model.User;
import com.nc.finalproject.service.SecurityService;
import com.nc.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for home page.
 *
 * @author WildDed
 * @version 1.0
 */

@Controller
public class HomeController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @GetMapping(URLs.HOME)
    public ModelAndView getHomePage() {
        return new ModelAndView(ViewNames.HOME)
                .addObject(LoggedIn.USER, new User(securityService.findLoggedInUsername()))
                .addObject(LoggedIn.ROLE, userService.findRoleByUsername(securityService.findLoggedInUsername()));
    }
}
