package com.emmett.bookclub.domain.bookclub.member;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookclubMemberService {
    ResponseEntity<List<BookclubMemberDto>> getBookclubMemberList(String clubId);

    ResponseEntity<BookclubMember> updateBookclubMember(String clubId, BookclubMember bookclubMember);
}
