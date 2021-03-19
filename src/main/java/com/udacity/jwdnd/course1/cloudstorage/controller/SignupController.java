package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Constant.MsgConstants;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView() {
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){

        //check if the username already exists
        if(!userService.isUsernameAvailable(user.getUsername())) {
            model.addAttribute("signupError", MsgConstants.signupError_userExists);
        } else if (userService.createUser(user) == 1) {
            model.addAttribute("signupSuccess", true); //successfully signed up
            return "login";
        } else {
            model.addAttribute("signupError", MsgConstants.signupError_other);
        }
        return "signup";
    }
}









