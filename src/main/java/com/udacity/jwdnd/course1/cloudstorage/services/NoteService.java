package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.Constant.CategoryConstants;
import com.udacity.jwdnd.course1.cloudstorage.Constant.MsgConstants;
import com.udacity.jwdnd.course1.cloudstorage.Constant.SizeConstants;
import com.udacity.jwdnd.course1.cloudstorage.Exception.NoteException;
import com.udacity.jwdnd.course1.cloudstorage.Constant.TabConstants;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
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

    public String addNote(NoteForm noteForm, int userId, Model model){
        try {
            //check if note size exceed limit
            if (noteForm.getNoteDescription().length() > SizeConstants.noteMaxSize) {
                throw new NoteException(MsgConstants.noteError_exceedLimit);
            }
            //add note
            if (noteMapper.addNote(new Note(null, noteForm.getNoteTitle(), noteForm.getNoteDescription(), userId)) == 1) {
                model.addAttribute("success", MsgConstants.getSuccessMsg_add(CategoryConstants.note));
                model.addAttribute("tabAfterSuccess", TabConstants.note);
            } else {
                throw new NoteException(MsgConstants.defaultError);
            }
        } catch(NoteException ne) {
            throw ne;
        } catch(Exception e) {
            throw new NoteException(MsgConstants.defaultError, e);
        }
        return "result";
    }

    public List<Note> getAllNote(int userId) { return noteMapper.getAllNote(userId);}

    public String deleteNode(int noteId, Model model) {
        try {
            //delete a note
            if (noteMapper.deleteNote(noteId) == 1) {
                model.addAttribute("success", MsgConstants.getSuccessMsg_delete(CategoryConstants.note));
                model.addAttribute("tabAfterSuccess", TabConstants.note);
            } else {
                throw new NoteException(MsgConstants.defaultError);
            }
        } catch(NoteException ne) {
            throw ne;
        } catch(Exception e) {
            throw new NoteException(MsgConstants.defaultError, e);
        }
        return "result";
    }

    public String updateNode(NoteForm noteForm, int noteId, Model model) {
        try {
            //edit a note
            if (noteMapper.updateNote(noteId, noteForm.getNoteTitle(), noteForm.getNoteDescription()) == 1) {
                model.addAttribute("success",  MsgConstants.getSuccessMsg_edit(CategoryConstants.note));
                model.addAttribute("tabAfterSuccess", TabConstants.note);
            } else {
                throw new NoteException(MsgConstants.defaultError);
            }
        } catch(NoteException ne) {
            throw ne;
        } catch(Exception e) {
            throw new NoteException(MsgConstants.defaultError, e);
        }
        return "result";
    }
}