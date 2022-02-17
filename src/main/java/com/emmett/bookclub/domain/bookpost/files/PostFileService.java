package com.emmett.bookclub.domain.bookpost.files;

import org.springframework.core.io.Resource;

import javax.transaction.Transactional;

@Transactional
public interface PostFileService {
    Resource getDownloadFile(String fileName, Integer boardFileId);

    Resource loadFileAsResource(String fileName);

    String getFileName(Integer boardFileId);

    String getDownloadFileUri(String fileName);
}
