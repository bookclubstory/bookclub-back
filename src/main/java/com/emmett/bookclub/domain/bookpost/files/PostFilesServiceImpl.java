package com.emmett.bookclub.domain.bookpost.files;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostFilesServiceImpl implements PostFilesService {
    private final Path fileStorageLocation;
    private final PostFilesRepository postFilesRepository;

    @Value("/api/v1/bookpost/files/download/")
    private String IMG_API;

    @Override
    public Resource getDownloadFile(String fileName, Integer boardFileId) {
        Resource resource = null;
        String originalFileName = fileName;

        if (boardFileId != null) {
            originalFileName = this.getFileName(boardFileId);
        }

        resource = this.loadFileAsResource(originalFileName);

        return resource;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        
        try {
            Resource resource = new UrlResource(filePath.toUri());

            if(!resource.exists()){
                throw new RuntimeException("파일을 찾을 수 없습니다." + fileName);
            }

            return resource;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("파일을 찾을 수 없습니다." + fileName);
        }
    }

    @Override
    public String getFileName(Integer boardFileId) {
        Optional<PostFiles> postFile = postFilesRepository.findById(boardFileId);
        return postFile.map(file -> file.fileName)
                .orElse("fileNameError");
    }

    @Override
    public String getDownloadFileUri(String fileName) {
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(IMG_API)
                    .path(fileName)
                    .toUriString();
    }
}
