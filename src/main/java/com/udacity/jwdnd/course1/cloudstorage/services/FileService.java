package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@Service
public class FileService implements StorageService{

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public void deleteFile(int fileId) {
        fileMapper.deleteFile(fileId);
    }

    @Override
    public List<File> getAllFile(int userId) {
        return fileMapper.getAllFile(userId);
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
    public void upload(MultipartFile file, int userId) {
        try {
            if (!file.isEmpty()) {

                fileMapper.insertFile(new File(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), userId, file.getBytes()));
            }
        }
        catch (Exception e) {

        }
    }
}
