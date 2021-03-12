package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

public interface StorageService {
    void deleteFile(int fileId);
    List<File> getAllFile(int userId);
    ResponseEntity<Resource> viewFile(int fileId);
    void upload(MultipartFile file, int userId);
}
