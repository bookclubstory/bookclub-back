package com.emmett.bookclub.domain.bookclub.member;

import com.emmett.bookclub.domain.model.exception.BadRequestException;
import com.emmett.bookclub.domain.model.exception.NotFoundException;
import com.emmett.bookclub.domain.system.code.CodeRepository;
import com.emmett.bookclub.domain.system.code.UpperCode;
import com.emmett.bookclub.global.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookclubMemberServiceImpl implements BookclubMemberService{
    private final Util util;
    private final BookclubMemberRepository bookclubMemberRepository;
    private final CodeRepository codeRepository;


    @Override
    public ResponseEntity<BookclubMemberDto> getBookclubMember(String clubId, int memberId) {
        BookclubMemberDto member = bookclubMemberRepository.findBookclubMember(clubId, memberId)
                .orElseThrow(() -> new NotFoundException("해당 멤버가 존재하지 않습니다"));
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookclubMemberDto>> getBookclubMemberList(String clubId) {
        List<BookclubMemberDto> list = bookclubMemberRepository.findBookclubMemberList(clubId)
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if(result.isEmpty())throw new NotFoundException("북클럽 멤버가 존재하지 않습니다.");
                    return result;
                }));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookclubMember> updateBookclubMember(String clubId, BookclubMember bookclubMember) {
        String loginId = util.getLoginId();
        int memberId = bookclubMember.getMemberId();
        String clubAuth = bookclubMember.getClubAuth();

        if(!isBookClubOwner(loginId))
            throw new BadRequestException(ClubAuth.CLUB_OWNER.getValue() +"만 권한 설정이 가능합니다.");

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

    @Override
    public ResponseEntity<BookclubMember> banBookclubMember(String clubId, BookclubMember bookclubMember) {
        bookclubMember.setClubAuth(ClubAuth.BANNED.getCode());

        return updateBookclubMember(clubId, bookclubMember);
    }

    @Override
    public boolean isBookClubOwner(String username) {
        BookclubMember member = bookclubMemberRepository.findBookclubMemberByUsername(username)
                .orElseThrow(() -> new NotFoundException(username + "이 해당 북클럽 멤버에 포함되지 않습니다"));

        if(ClubAuth.CLUB_OWNER.getCode().equals(member.getClubAuth()))return true;
        return false;
    }
}
