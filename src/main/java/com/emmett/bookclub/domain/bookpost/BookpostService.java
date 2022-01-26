package com.emmett.bookclub.domain.bookpost;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookpostService {
    ResponseEntity<List<BookpostDto>> getBookpostList();
}
