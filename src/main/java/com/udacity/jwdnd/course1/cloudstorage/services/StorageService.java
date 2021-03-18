package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

public interface StorageService {
    int deleteFile(int fileId);
    List<File> getAllFile(int userId);
    int checkFile(int userId, String fileName);
    ResponseEntity<Resource> viewFile(int fileId);
    String upload(MultipartFile file, int userId, Model model);
}
