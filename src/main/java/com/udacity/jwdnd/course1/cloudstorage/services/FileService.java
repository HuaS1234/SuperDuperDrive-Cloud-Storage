package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    public void upload(MultipartFile file, int userId) {
        try {
            if (!file.isEmpty()) {

                fileMapper.insertFile(new File(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), userId, file.getBytes()));





//                Path destinationFile = this.rootLocation.resolve(
//                        Paths.get(file.getOriginalFilename()))
//                        .normalize().toAbsolutePath();
//
//
////                if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
////                    // This is a security check
////                    throw new StorageException(
////                            "Cannot store file outside current directory.");
////                }
//
//                try (InputStream inputStream = file.getInputStream()) {
//                    Files.copy(inputStream, destinationFile,
//                            StandardCopyOption.REPLACE_EXISTING); //need to check dup
//                }




            }

        }
        catch (Exception e) {

        }


    }


}
