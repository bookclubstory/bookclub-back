package com.emmett.bookclub.domain.bookclub.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookclub")
public class BookclubMemberContoroller {
    private final BookclubMemberService bookclubMemberService;

    /**
     * Subject: 북클럽 멤버 조회
     * Description:
     */
    @GetMapping("/{clubId}/member/list")
    public ResponseEntity<List<BookclubMemberDto>> getBookclubMemberList(@PathVariable String clubId) {
        return bookclubMemberService.getBookclubMemberList(clubId);
    }

    /**
     * Subject: 북클럽 멤버 변경
     * Description:
     */
    @PutMapping("/{clubId}/member")
    public ResponseEntity<BookclubMember> updateBookclubMember(@PathVariable String clubId, @RequestBody BookclubMember bookclubMember) {
        return bookclubMemberService.updateBookclubMember(clubId, bookclubMember);
    }
}
