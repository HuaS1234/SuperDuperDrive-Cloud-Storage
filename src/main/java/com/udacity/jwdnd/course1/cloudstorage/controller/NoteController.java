package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/edit")
    public String addNote(NoteForm noteForm, Authentication authentication, Model model, @ModelAttribute(value="noteId") String noteId) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        if (noteId.equals("")) {
            if (noteService.addNote(noteForm, userId, model) == 1) {
                model.addAttribute("success", true);
                model.addAttribute("tabAfterSuccess", "notes");
            } else {
                model.addAttribute("otherError", true);
                model.addAttribute("tabAfterOtherError", "notes");
            }
        } else {
            if(noteService.updateNode(noteForm, Integer.valueOf(noteId)) == 1){
                model.addAttribute("success", true);
                model.addAttribute("tabAfterSuccess", "notes");
            } else {
                model.addAttribute("otherError", true);
                model.addAttribute("tabAfterOtherError", "notes");
            }
        }
        return "result";
    }

    @RequestMapping(value="/delete/{noteId}")
    public String deleteNote(Model model, @PathVariable int noteId){
        if (noteService.deleteNode(noteId) == 1) {
            model.addAttribute("success", true);
            model.addAttribute("tabAfterSuccess", "notes");
        } else {
            model.addAttribute("otherError", true);
            model.addAttribute("tabAfterOtherError", "notes");
        }
        return "result";
    }
}
