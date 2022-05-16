package com.emmett.bookclub.domain.bookclub.member;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookclubMemberService {
    ResponseEntity<BookclubMemberDto> getBookclubMember(String clubId, int memberId);

    ResponseEntity<List<BookclubMemberDto>> getBookclubMemberList(String clubId);

    ResponseEntity<BookclubMember> updateBookclubMember(String clubId, BookclubMember bookclubMember);

    ResponseEntity<BookclubMember> banBookclubMember(String clubId, BookclubMember bookclubMember);

    boolean isBookClubOwner(String username);
}
