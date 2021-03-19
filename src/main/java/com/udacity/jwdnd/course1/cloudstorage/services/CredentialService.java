package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Constant.CategoryConstants;
import com.udacity.jwdnd.course1.cloudstorage.Constant.MsgConstants;
import com.udacity.jwdnd.course1.cloudstorage.Constant.TabConstants;
import com.udacity.jwdnd.course1.cloudstorage.Exception.CredentialException;
import com.udacity.jwdnd.course1.cloudstorage.Exception.FileException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
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
        try {
            //encrypted password first
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            String key = Base64.getEncoder().encodeToString(salt);
            String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);

            if (credentialMapper.addCredential(new Credential(null, credentialForm.getUrl(), credentialForm.getUsername(), key, encryptedPassword, userId)) == 1) {
                model.addAttribute("success", MsgConstants.getSuccessMsg_add(CategoryConstants.credential));
                model.addAttribute("tabAfterSuccess", TabConstants.credential);
            } else {
                throw new CredentialException(MsgConstants.defaultError);
            }
        } catch(CredentialException ce) {
            throw ce;
        } catch(Exception e) {
            throw new CredentialException(MsgConstants.defaultError, e);
        }
        return "result";
    }

    public List<Credential> getAllCredential(int userId) {
        return credentialMapper.getAllCredential(userId);
    }

    public String deleteCredential(int credentialId, Model model) {
        try {
            //delete a credential
            if (credentialMapper.deleteCredential(credentialId) == 1) {
                model.addAttribute("success", MsgConstants.getSuccessMsg_delete(CategoryConstants.credential));
                model.addAttribute("tabAfterSuccess", TabConstants.credential);
            } else {
                throw new CredentialException(MsgConstants.defaultError);
            }
        } catch(CredentialException ce) {
            throw ce;
        } catch(Exception e) {
            throw new CredentialException(MsgConstants.defaultError, e);
        }
        return "result";
    }

    public String updateCredential(CredentialForm credentialForm, int credentialId, Model model) {
        try {
            //getKey
            String key = credentialMapper.getKey(credentialId);
            String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);
            if (credentialMapper.updateCredential(credentialId, credentialForm.getUrl(), credentialForm.getUsername(), encryptedPassword) == 1) {
                model.addAttribute("success", MsgConstants.getSuccessMsg_edit(CategoryConstants.credential));
                model.addAttribute("tabAfterSuccess", TabConstants.credential);
            } else {
                throw new CredentialException(MsgConstants.defaultError);
            }
        } catch(CredentialException ce) {
            throw ce;
        } catch(Exception e) {
            throw new CredentialException(MsgConstants.defaultError, e);
        }

        return "result";
    }
}
