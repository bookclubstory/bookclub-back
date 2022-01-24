package com.emmett.bookclub.domain.bookclub.library;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BookLibraryServiceImpl implements BookLibraryService {
    private final BookLibraryRepository bookLibraryRepository;

    @Override
    public ResponseEntity<List<Map<String, Object>>> getBookLibraryList() {
        return null;
    }
}
