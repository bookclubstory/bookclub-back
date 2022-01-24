package com.emmett.bookclub.domain.bookpost;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BookpostServiceImpl implements BookpostService {
    private final BookpostRepository bookpostRepository;

    @Override
    public ResponseEntity<List<Map<String, Object>>> getBookpostList() {
        return null;
    }
}
