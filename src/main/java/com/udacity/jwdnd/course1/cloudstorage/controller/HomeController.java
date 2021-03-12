package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final UserService userService;

    public HomeController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    public int getUserId(Principal principal) {
        String userName = principal.getName(); //get current username
        User user = userService.getUser(userName);
        return user.getUserId();
    }

    @GetMapping
    public String homeView(Model model, Principal principal) {
        int userId = getUserId(principal);

        model.addAttribute("fileList", this.fileService.getAllFile(userId));
        return "home";
    }

    @PostMapping
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model, Principal principal) {

        int userId = getUserId(principal);
        fileService.upload(file, userId);


        model.addAttribute("fileList", this.fileService.getAllFile(userId));
        return "home";
    }

    @RequestMapping(value="/edit/{fileId}")
    public String deleteFile(@PathVariable int fileId, Model model, Principal principal){
        int userId = getUserId(principal);
        fileService.deleteFile(fileId);
        System.out.println(fileId + " ");
        return "redirect:/home";
    }

}