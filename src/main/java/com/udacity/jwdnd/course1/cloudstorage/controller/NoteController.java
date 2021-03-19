package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return noteService.addNote(noteForm, userId, model);
        } else {
            return noteService.updateNode(noteForm, Integer.valueOf(noteId), model);
        }
    }

    @RequestMapping(value="/delete/{noteId}")
    public String deleteNote(Model model, @PathVariable int noteId){
        return noteService.deleteNode(noteId, model);
    }
}
