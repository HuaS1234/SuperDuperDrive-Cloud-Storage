package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/edit")
    public String addCredential(CredentialForm credentialForm, Authentication authentication, Model model, @ModelAttribute(value="credentialId") String credentialId) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        if (credentialId.equals("")) {
            if (credentialService.addCredential(credentialForm, userId, model) == 1) {
                model.addAttribute("success", true);
                model.addAttribute("tabAfterSuccess", "credentials");
            } else {
                model.addAttribute("otherError", true);
                model.addAttribute("tabAfterOtherError", "credentials");
            }
        } else {
            if (credentialService.updateCredential(credentialForm, Integer.valueOf(credentialId)) == 1) {
                model.addAttribute("success", true);
                model.addAttribute("tabAfterSuccess", "credentials");
            } else {
                model.addAttribute("otherError", true);
                model.addAttribute("tabAfterOtherError", "credentials");
            }
        }
        return "result";
    }

    @RequestMapping(value="/delete/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId, Model model){
        if (credentialService.deleteCredential(credentialId) == 1) {
            model.addAttribute("success", true);
            model.addAttribute("tabAfterSuccess", "credentials");
        } else {
            model.addAttribute("otherError", true);
            model.addAttribute("tabAfterOtherError", "credentials");
        }
        return "result";
    }
}
