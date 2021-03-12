package com.udacity.jwdnd.course1.cloudstorage.model;

import java.nio.file.Path;

public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private Long filesize;
    private Integer userid;
    private byte[] filedata; //byte[] -> BLOB


    public File(Integer fileId, String filename, String contenttype, Long filesize, Integer userid, byte[] filedata) {
        this.fileId = fileId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getFilename() {
        return filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public Long getFilesize() {
        return filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }
}




//ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());