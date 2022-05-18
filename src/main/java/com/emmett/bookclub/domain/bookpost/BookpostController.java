package com.emmett.bookclub.domain.bookpost;

import com.emmett.bookclub.domain.model.vo.BookpostFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookpost")
public class BookpostController {
    private final BookpostService bookpostService;

    /**
     * Subject: 독서로그 목록 조회
     * Description:
     */
    @GetMapping("/list")
    public ResponseEntity<Page<BookpostDto>> getBookpostList(Pageable pageable) {
        return bookpostService.getBookpostList(pageable);
    }

    /**
     * Subject: 독서로그 저장(신규)
     * Description:
     */
    @PostMapping
    public ResponseEntity<String> addNewBookpost(BookpostFileVo bookpostFileVo) throws Exception {
        return bookpostService.addNewBookpost(bookpostFileVo);
    }

}
