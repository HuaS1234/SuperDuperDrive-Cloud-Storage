package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Authentication authentication, Model model)  {
        int userId = userService.getUser(authentication.getName()).getUserId();
        return fileService.upload(file, userId, model);
    }

    @RequestMapping(value="/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId, Model model){
        return fileService.deleteFile(fileId, model);
    }

    @GetMapping(value="/view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable int fileId, Authentication authentication) {
        return fileService.viewFile(fileId);
    }

}
