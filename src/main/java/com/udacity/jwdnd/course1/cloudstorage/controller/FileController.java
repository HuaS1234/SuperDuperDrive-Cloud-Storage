package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home/file-upload")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    public int getUserId(Principal principal) {
        String userName = principal.getName(); //get current username
        User user = userService.getUser(userName);
        return user.getUserId();
    }

    @PostMapping
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model, Principal principal) {
        //InputStream fis = file.getInputStream();

        int userId = getUserId(principal);
        fileService.upload(file, userId);

        //list uploaded files:
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));

        //get all list
        List<File> list = fileService.getAllFile(userId);

        return "home";
    }
}
