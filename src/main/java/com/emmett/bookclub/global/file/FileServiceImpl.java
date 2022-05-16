package com.emmett.bookclub.global.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService{
    private final Path fileStorageLocation;

    @Override
    public FileDto
    uploadFile(MultipartFile file) {
        FileDto fileDto = new FileDto();
        if(file==null||file.isEmpty())return fileDto;

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String ext = fileName.substring(fileName.lastIndexOf("."));

        UUID uuid = UUID.randomUUID();
        String storedName = uuid + ext;

        Path targetLocation = this.fileStorageLocation.resolve(storedName);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("파일 " + fileName + " 저장 불가.", e);
        }

        fileDto.setFileName(fileName);
        fileDto.setExt(ext);
        fileDto.setStoredName(storedName);

        return fileDto;
    }
}
