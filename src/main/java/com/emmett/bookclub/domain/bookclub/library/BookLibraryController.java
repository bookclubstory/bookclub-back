package com.emmett.bookclub.domain.bookclub.library;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/library")
public class BookLibraryController {
    private final BookLibraryService bookLibraryService;

    /**
     * Subject:
     * Description:
     * */
    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> getBookLibraryList() {
        return bookLibraryService.getBookLibraryList();
    }
}
