package com.emmett.bookclub.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ConfigurationProperties(prefix = "file")
@Getter
@Setter
@Configuration
public class FileStorageConfig {
    @Value("${file.upload-dir}")
    String uploadDir;

    @Value("${file.upload-root}")
    String uploadRoot;

    @Bean
    public Path fileStorageLocation() throws IOException {
        Path fileStorageLocation = Paths.get(uploadRoot).toAbsolutePath().normalize();
        return Files.createDirectories(fileStorageLocation);
    }
}
