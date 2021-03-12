package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    void deleteFile(int fileId);
    List<File> getAllFile(int userId);
    void upload(MultipartFile file, int userId);
}
