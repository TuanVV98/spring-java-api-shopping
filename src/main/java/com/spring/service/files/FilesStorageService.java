package com.spring.service.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesStorageService {

    void save(String filename,MultipartFile file);

    byte[] downloadFtpFile(String filename) throws IOException;
}
