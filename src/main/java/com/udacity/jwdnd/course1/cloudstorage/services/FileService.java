package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Constant.CategoryConstants;
import com.udacity.jwdnd.course1.cloudstorage.Constant.MsgConstants;
import com.udacity.jwdnd.course1.cloudstorage.Constant.TabConstants;
import com.udacity.jwdnd.course1.cloudstorage.Exception.FileException;
import com.udacity.jwdnd.course1.cloudstorage.Exception.NoteException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public String deleteFile(int fileId, Model model) {
        try {
            //delete a file
            if (fileMapper.deleteFile(fileId) == 1) {
                model.addAttribute("success", MsgConstants.getSuccessMsg_delete(CategoryConstants.file));
                model.addAttribute("tabAfterSuccess", TabConstants.file);
            } else {
                throw new FileException(MsgConstants.defaultError);
            }
        } catch(FileException fe) {
            throw fe;
        } catch(Exception e) {
            throw new FileException(MsgConstants.defaultError, e);
        }

        return "result";
    }

    public List<File> getAllFile(int userId) {
        return fileMapper.getAllFile(userId);
    }

    public int checkFile(int userId, String fileName) {
        return fileMapper.checkFile(userId, fileName);
    }

    public ResponseEntity<Resource> viewFile(int fileId) {
        try {
            File file = fileMapper.getFile(fileId);
            ResponseEntity respEntity = null;
            String type = file.getContenttype();
            byte[] out = file.getFiledata();

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-disposition", "attachment; filename=" + file.getFilename());
            responseHeaders.add("Content-Type", type);

            respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK);

            return respEntity;
        } catch(Exception e) {
            throw new FileException(MsgConstants.defaultError, e);
        }
    }

    public String upload(MultipartFile file, int userId, Model model) {
        try {
            //check if user selected a file
            if (!file.isEmpty()) {
                if (checkFile(userId, file.getOriginalFilename()) > 0) {
                    throw new FileException(MsgConstants.fileError_duplicate);
                } else {
                    if (fileMapper.insertFile(new File(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), userId, file.getBytes())) == 1) {
                        model.addAttribute("success", MsgConstants.getSuccessMsg_add(CategoryConstants.file));
                        model.addAttribute("tabAfterSuccess", TabConstants.file);
                    } else {
                        throw new FileException(MsgConstants.defaultError);
                    }
                }
            } else {
                throw new FileException(MsgConstants.fileError_empty);
            }
        } catch(FileException fe) {
            throw fe;
        } catch(Exception e) {
            throw new FileException(MsgConstants.defaultError, e);
        }

        return "result";
    }
}
