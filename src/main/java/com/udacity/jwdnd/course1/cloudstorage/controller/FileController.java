package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

//    @GetMapping
//    public String homeView(Model model, Authentication authentication) {
//        int userId = userService.getUser(authentication.getName()).getUserId();
//        model.addAttribute("fileList", this.fileService.getAllFile(userId));
//        //model.addAttribute("activeTab", "notes");
//        return "home";
//    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Authentication authentication, Model model) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        return fileService.upload(file, userId, model);
    }

    @RequestMapping(value="/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId){
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }

    @GetMapping(value="/view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable int fileId, Authentication authentication) {
        return fileService.viewFile(fileId);
    }

}
