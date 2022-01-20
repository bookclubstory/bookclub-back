package com.emmett.bookclub.domain.bookclub.library;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface BookLibraryService {
    ResponseEntity<List<Map<String, Object>>> getBookLibraryList();
}
