package com.emmett.bookclub.domain.bookclub.session;

import com.emmett.bookclub.domain.bookclub.BookclubDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookclub/session")
public class BookclubSessionController {
    private final BookclubSessionService bookclubSessionService;

    /**
     * Subject: 북클럽 세션 목록 조회
     * Description:
     */
    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> getBookclubSessionList() {
        return bookclubSessionService.getBookclubSessionList();
    }

    /**
     * Subject: 북클럽 세션 생성
     * Description:
     */
    @PostMapping
    public ResponseEntity<String> addBookclubSession(@RequestBody BookclubSessionDto bookclubSessionDto) {
        return bookclubSessionService.addBookclubSession(bookclubSessionDto);
    }

    /**
     * Subject: 북클럽 세션 정보 수정
     * Description:
     */
    @PutMapping
    public ResponseEntity<String> editBookclubSession(@RequestBody BookclubSessionDto bookclubSessionDto) {
        return bookclubSessionService.editBookclubSession(bookclubSessionDto);
    }

    /**
     * Subject: 북클럽 세션 종료
     * Description:
     */
    @PutMapping("/close")
    public ResponseEntity<String> closeBookclubSession() {
        return bookclubSessionService.closeBookclubSession();
    }

    /**
     * Subject: 북클럽 세션 상세 조회
     * Description:
     */
    @GetMapping("/{sessionId}")
    public ResponseEntity<Map<String, Object>> getBookclubSessionDetail(@PathVariable String sessionId) {
        return bookclubSessionService.getBookclubSessionDetail(sessionId);
    }
}
