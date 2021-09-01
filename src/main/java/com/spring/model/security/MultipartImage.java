package com.spring.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MultipartImage implements MultipartFile , Serializable {
    private static final long serialVersionUID = 7417500052547882043L;

    private byte[] bytes;
    String name;
    String originalFilename;
    String contentType;
    boolean isEmpty;
    long size;

    public MultipartImage(byte[] bytes, String name, String originalFilename, String contentType,
                          long size) {
        this.bytes = bytes;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.isEmpty = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        // TODO Auto-generated method stub

    }
}
