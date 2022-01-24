package com.emmett.bookclub.domain.bookclub;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookclub")
public class BookclubController {
    private final BookclubService bookclubService;

    /**
     * Subject: 북클럽 목록 조회
     * Description:
     */
    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> searchBookclub(@RequestParam String keyword) {
        return bookclubService.searchBookclub(keyword);
    }

    /**
     * Subject: 북클럽 개설
     * Description:
     */
    @PostMapping
    public ResponseEntity<String> addBookclub(@RequestBody BookclubDto bookclubDto) {
        return bookclubService.addBookclub(bookclubDto);
    }

    /**
     * Subject: 북클럽 정보 수정
     * Description:
     */
    @PutMapping
    public ResponseEntity<String> editBookclub(@RequestBody BookclubDto bookclubDto) {
        return bookclubService.editBookclub(bookclubDto);
    }

    /**
     * Subject: 북클럽 폐쇄
     * Description:
     */
    @PutMapping("/close")
    public ResponseEntity<String> closeBookclub() {
        return bookclubService.closeBookclub();
    }

    /**
     * Subject: 북클럽 목록 조회
     * Description:
     */
    @GetMapping("/{clubId}")
    public ResponseEntity<Map<String, Object>> getBookclubDetail(@PathVariable String clubId) {
        return bookclubService.getBookclubDetail(clubId);
    }
}
