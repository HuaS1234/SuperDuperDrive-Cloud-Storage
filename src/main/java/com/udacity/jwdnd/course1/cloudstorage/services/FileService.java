package com.udacity.jwdnd.course1.cloudstorage.services;

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
public class FileService implements StorageService{

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public int deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }

    @Override
    public List<File> getAllFile(int userId) {
        return fileMapper.getAllFile(userId);
    }

    @Override
    public int checkFile(int userId, String fileName) {
        return fileMapper.checkFile(userId, fileName);
    }

    @Override
    public ResponseEntity<Resource> viewFile(int fileId) {
        File file = fileMapper.getFile(fileId);
        ResponseEntity respEntity = null;
        String type=file.getContenttype();
        byte[]out=file.getFiledata();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=" + file.getFilename());
        responseHeaders.add("Content-Type",type);

        respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK);

        return respEntity;
    }

    @Override
    public String upload(MultipartFile file, int userId, Model model) {

        try {
            if (!file.isEmpty()) {
                //checkFile(userId, file.getOriginalFilename());
                if (checkFile(userId, file.getOriginalFilename()) > 0) {
                    model.addAttribute("error", "The file has been uploaded already! ");
                    model.addAttribute("tabAfterError", "files");
                } else {
                    if (fileMapper.insertFile(new File(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), userId, file.getBytes())) == 1) {
                        model.addAttribute("success", true);
                        model.addAttribute("tabAfterSuccess", "files");
                    } else {
                        model.addAttribute("otherError", true);
                        model.addAttribute("tabAfterOtherError", "files");
                    }
                }
            } else {
                model.addAttribute("error", "Choose a file first! ");
                model.addAttribute("tabAfterError", "files");
            }

            return "result";
        }
        catch (Exception e) {
            model.addAttribute("otherError", "error");
            model.addAttribute("tabAfterOtherError", "files");
            return "result";
        }
    }
}
