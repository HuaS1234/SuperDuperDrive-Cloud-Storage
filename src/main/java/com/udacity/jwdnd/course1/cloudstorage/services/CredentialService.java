package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public String addCredential(CredentialForm credentialForm, int userId, Model model) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String key = Base64.getEncoder().encodeToString(salt);
        String encrypedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);

        credentialMapper.addCredential(new Credential(null, credentialForm.getUrl(), credentialForm.getUsername(),key, encrypedPassword, userId));
        return "redirect:/home?tabOption=credentials";
    }

    public List<Credential> getAllCredential(int userId) {
        return credentialMapper.getAllCredential(userId);
    }

    public void deleteCredential(int credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

    public String updateCredential(CredentialForm credentialForm, int credentialId) {
        //getKey
        String key = credentialMapper.getKey(credentialId);
        String encrypedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);
        credentialMapper.updateCredential(credentialId, credentialForm.getUrl(), credentialForm.getUsername(), encrypedPassword);
        return "redirect:/home?tabOption=credentials";
    }
}
