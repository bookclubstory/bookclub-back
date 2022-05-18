package com.emmett.bookclub.domain.bookpost;

import com.emmett.bookclub.domain.model.vo.BookpostFileVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookpostService {
    ResponseEntity<List<BookpostDto>> getBookpostList();

    ResponseEntity<Page<BookpostDto>> getBookpostList(Pageable pageable);

    ResponseEntity<String> addNewBookpost(BookpostFileVo bookpostFileVo) throws Exception;
}
