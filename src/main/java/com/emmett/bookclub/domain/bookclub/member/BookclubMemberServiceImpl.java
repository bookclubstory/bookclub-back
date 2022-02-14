package com.emmett.bookclub.domain.bookclub.member;

import com.emmett.bookclub.domain.model.exception.NotFoundException;
import com.emmett.bookclub.domain.system.code.CodeRepository;
import com.emmett.bookclub.domain.system.code.UpperCode;
import com.emmett.bookclub.global.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookclubMemberServiceImpl implements BookclubMemberService{
    private final Util util;
    private final BookclubMemberRepository bookclubMemberRepository;
    private final CodeRepository codeRepository;


    @Override
    public ResponseEntity<List<BookclubMemberDto>> getBookclubMemberList(String clubId) {
        List<BookclubMemberDto>  list = bookclubMemberRepository.findBookclubMember(clubId, UpperCode.CLUB_AUTH.getCode());

        if(list.isEmpty())throw new NotFoundException("북클럽 멤버가 존재하지 않습니다.");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookclubMember> updateBookclubMember(String clubId, BookclubMember bookclubMember) {
        String loginId = util.getLoginId();
        int memberId = bookclubMember.getMemberId();
        String clubAuth = bookclubMember.getClubAuth();

        BookclubMember member = bookclubMemberRepository.findById(memberId)
                .orElseThrow(()->new NotFoundException("해당 멤버가 존재하지 않습니다"));

        //권한 valiation
        codeRepository.findByUpperCode(UpperCode.CLUB_AUTH.getCode())
                .stream().filter(code -> code.getCode().equals(clubAuth)).findFirst()
                .orElseThrow(()->new NotFoundException("[" +clubAuth + "] 권한이 존재하지않습니다. "));

        member.setClubAuth(clubAuth);
        member.setModifiedBy(loginId);
        bookclubMemberRepository.save(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
