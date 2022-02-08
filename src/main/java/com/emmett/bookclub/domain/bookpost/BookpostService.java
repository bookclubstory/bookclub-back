package com.emmett.bookclub.domain.bookpost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookpostService {
    ResponseEntity<List<BookpostDto>> getBookpostList();

    ResponseEntity<Page<BookpostDto>> getBookpostList(Pageable pageable);
}
