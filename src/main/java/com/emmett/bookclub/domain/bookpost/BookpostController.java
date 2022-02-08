package com.emmett.bookclub.domain.bookpost;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookpost")
public class BookpostController {
    private final BookpostService bookpostService;

    /**
     * Subject: 독서로그 목록 조회
     * Description:
     * */
    @GetMapping("/list")
    public ResponseEntity<Page<BookpostDto>> getBookpostList(Pageable pageable){
        return bookpostService.getBookpostList(pageable);
    }
}
