package com.spring.service.files;

import com.spring.model.security.MultipartImage;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import net.coobird.thumbnailator.resizers.configurations.Antialiasing;
import net.coobird.thumbnailator.resizers.configurations.Rendering;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    @Value("${ftp.server.hostname}")
    private String hostname;

    @Value("${ftp.server.port}")
    private int port;

    @Value("${ftp.server.username}")
    private String username;

    @Value("${ftp.server.password}")
    private String password;

//    public FTPClient ftpClient = null;

//    public void getConnectFTP() {
//        ftpClient = new FTPClient();
//        ftpClient.setRemoteVerificationEnabled(false); // Cancel server Ip addresses and host get their submission match, otherwise it will report an exception when inconsistent.
//        ftpClient.setControlEncoding("utf-8");
//        try {
////            ftpClient.setDataTimeout(1000 * 1200);
//            ftpClient.connect(hostname, port);
//            ftpClient.login(username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    @Override
    public void save(String filename, MultipartFile file) {

//            BufferedImage originalImage = ImageIO.read(file.getInputStream());
//            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        FTPClient ftpClient = new FTPClient();
        if (file != null) {
            try {
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    Thumbnails.of(originalImage)
//                            .size(originalImage.getWidth() / 3, originalImage.getHeight() / 3)
//                            .outputQuality(1.0)
//                            .outputFormat(extension)
//                            .toOutputStream(outputStream);
//
//                    System.out.println("size :" + outputStream.size());
//                    byte[] data = outputStream.toByteArray();
//                    ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

                ftpClient.connect(hostname, port);
                if (ftpClient.login(username, password)) {

                    ftpClient.enterLocalPassiveMode(); //important !
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                    ftpClient.storeFile(filename, file.getInputStream());
//
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                System.out.println("file name :" + filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ByteArrayInputStream decrease(BufferedImage originalImage, int targetWidth, int targetHeight, String extension) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(originalImage)
                .size(targetWidth, targetHeight)
                .outputQuality(1.0)
                .outputFormat(extension)
                .toOutputStream(outputStream);

        System.out.println("size :" + outputStream.size());
        byte[] data = outputStream.toByteArray();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        return new ByteArrayInputStream(data);
    }

    @Override
    public byte[] downloadFtpFile(String filename) throws IOException {

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostname, port);
            if (ftpClient.login(username, password)) {

                ftpClient.enterLocalPassiveMode(); //important !
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    ftpClient.retrieveFile(filename, outputStream);

//                    byte[] bytes = outputStream.toByteArray();
//                    System.out.println("size :" + outputStream.size());
//                    InputStream is = new ByteArrayInputStream(bytes);
////                  String mimeType = URLConnection.guessContentTypeFromStream(is);
//                    BufferedImage originalImage = ImageIO.read(is);
//                    String extension = FilenameUtils.getExtension(filename);
//                    ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
//                    Thumbnails.of(originalImage)
//                            .size(originalImage.getWidth() * 4, originalImage.getHeight() * 4)
//                            .outputQuality(1.0)
//                            .outputFormat(extension)
//                            .toOutputStream(newOutputStream);

                    return outputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ftpClient.logout();
            ftpClient.disconnect();

        }
        return null;

    }
}
