package com.example.demo.core.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    FileUploadResponse storeFile(MultipartFile file);
}
