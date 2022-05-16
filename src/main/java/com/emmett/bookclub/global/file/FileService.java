package com.emmett.bookclub.global.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileDto uploadFile(MultipartFile file);
}
