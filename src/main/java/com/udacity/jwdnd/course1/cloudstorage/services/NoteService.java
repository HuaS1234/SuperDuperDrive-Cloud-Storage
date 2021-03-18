package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int addNote(NoteForm noteForm, int userId, Model model) {
        return noteMapper.addNote(new Note(null, noteForm.getNoteTitle(), noteForm.getNoteDescription(), userId));
    }

    public List<Note> getAllNote(int userId) { return noteMapper.getAllNote(userId);}

    public int deleteNode(int noteId) {
        return noteMapper.deleteNote(noteId);
    }

    public int updateNode(NoteForm noteForm, int noteId) {
        return noteMapper.updateNote(noteId, noteForm.getNoteTitle(), noteForm.getNoteDescription());
    }
}