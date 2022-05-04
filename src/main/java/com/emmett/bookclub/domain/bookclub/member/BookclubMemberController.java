package com.emmett.bookclub.domain.bookclub.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookclub/member")
public class BookclubMemberController {
    private final BookclubMemberService bookclubMemberService;
}
