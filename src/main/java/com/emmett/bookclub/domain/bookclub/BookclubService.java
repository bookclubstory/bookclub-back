package com.emmett.bookclub.domain.bookclub;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface BookclubService {
    ResponseEntity<List<BookclubDto>> getBookclubList();

    ResponseEntity<List<Map<String, Object>>> searchBookclub(String keyword);

    ResponseEntity<String> addBookclub(BookclubDto bookclubDto);

    ResponseEntity<String> editBookclub(BookclubDto bookclubDto);

    ResponseEntity<String> closeBookclub();

    ResponseEntity<BookclubDto> getBookclubDetail(String clubId);
}
