package com.emmett.bookclub.domain.bookpost;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface BookpostService {
    ResponseEntity<List<Map<String, Object>>> getBookpostList();
}
