package com.emmett.bookclub.domain.bookclub.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookclubMemberServiceImpl implements BookclubMemberService {
    private final BookclubMemberRepository bookclubMemberRepository;
}
