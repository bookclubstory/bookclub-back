package com.emmett.bookclub.domain.model.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BookpostFileVo {
    private String memberId;
    private String title;
    private String content;
    private List<MultipartFile> files;
}
