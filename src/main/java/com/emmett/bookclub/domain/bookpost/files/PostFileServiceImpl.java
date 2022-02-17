package com.emmett.bookclub.domain.bookpost.files;

import com.emmett.bookclub.domain.model.exception.NotFoundException;
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
public class PostFileServiceImpl implements PostFileService {
    private final Path fileStorageLocation;
    private final PostFileRepository postFileRepository;

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
                throw new NotFoundException(fileName + " 을 찾을 수 없습니다.");
            }

            return resource;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new NotFoundException(fileName+ " 을 찾을 수 없습니다.");
        }
    }

    @Override
    public String getFileName(Integer boardFileId) {
        Optional<PostFile> postFile = postFileRepository.findById(boardFileId);
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
