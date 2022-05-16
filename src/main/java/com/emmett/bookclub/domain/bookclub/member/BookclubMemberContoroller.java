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
    @GetMapping("/{clubId}/member/{memberId}")
    public ResponseEntity<BookclubMemberDto> getBookclubMember(@PathVariable String clubId, @PathVariable int memberId) {
        return bookclubMemberService.getBookclubMember(clubId, memberId);
    }

    /**
     * Subject: 북클럽 멤버리스트 조회
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

    /**
     * Subject: 북클럽 멤버 퇴출
     * Description:
     */
    @PutMapping("/{clubId}/member/banned")
    public ResponseEntity<BookclubMember> banBookclubMember(@PathVariable String clubId, @RequestBody BookclubMember bookclubMember) {
        return bookclubMemberService.banBookclubMember(clubId, bookclubMember);
    }
}
