package com.emmett.bookclub.domain.bookclub;

import com.emmett.bookclub.domain.bookclub.member.BookclubMember;
import com.emmett.bookclub.domain.bookclub.member.BookclubMemberRepository;
import com.emmett.bookclub.domain.bookclub.member.ClubAuth;
import com.emmett.bookclub.domain.bookpost.files.PostFileService;
import com.emmett.bookclub.domain.model.exception.NotFoundException;
import com.emmett.bookclub.global.file.FileDto;
import com.emmett.bookclub.global.file.FileService;

import com.emmett.bookclub.global.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookclubServiceImpl implements BookclubService {
    private final BookclubRepository bookclubRepository;
    private final BookclubMemberRepository bookclubMemberRepository;
    private final PostFileService postFileService;
    private final FileService fileService;
    private final Util util;

    @Override
    public ResponseEntity<List<BookclubDto>> getBookclubList() {
        List<BookclubDto> list = bookclubRepository.findBookclubList()
                .stream()
                .map(bookclub ->{
                    BookclubDto bookclubDto = new BookclubDto(bookclub);
                    bookclubDto.setThumbnail(postFileService.getDownloadFileUri(bookclubDto.getThumbnail()));
                    return bookclubDto;
                })
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if(result.isEmpty())throw new NotFoundException("북클럽 멤버가 존재하지 않습니다.");
                    return result;
                }));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> searchBookclub(String keyword) {
        // TODO://add ~NotClosedYn(true)
        if(!StringUtils.hasText(keyword))keyword = "";
        List<Bookclub> list = bookclubRepository.findByClubNmOrClubLocOrClubIntroOrderByClubNmAsc(keyword, keyword, keyword);

        List<Map<String, Object>> result = new ArrayList<>();

        list.forEach(element -> {
            //mapping
            Map<String, Object> row = new HashMap<>();
            row.put("clubId", element.getClubId());
            row.put("clubNm", element.getClubNm());
            row.put("clubLoc", element.getClubLoc());
            row.put("clubMemberCnt", element.getClubMemberCnt());
            row.put("privateYn", element.getPrivateYn());
            row.put("clubIntro", element.getClubIntro());

            result.add(row);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    @Modifying
    public ResponseEntity<String> addBookclub(BookclubDto bookclubDto) {
        String loginId = util.getLoginId();

        FileDto fileDto = fileService.uploadFile(bookclubDto.getFile());
        String fileName = fileDto.getFileName();
        String ext = fileDto.getExt();
        String thumbnail = fileDto.getStoredName();

        //bookclub 저장
        Bookclub bookclub = new Bookclub();
        int totMemberCnt = Optional.ofNullable(bookclubDto.getTotMemberCnt()).orElse(0);
        boolean privateYn = Optional.ofNullable(bookclubDto.getPrivateYn()).orElse(false);
        bookclub.newBookclub(bookclubDto.getClubNm(),bookclubDto.getClubLoc(), totMemberCnt,
                privateYn, bookclubDto.getClubIntro(), fileName, ext, thumbnail, loginId, loginId);

        bookclubRepository.save(bookclub);

        String clubId = bookclub.getClubId();

        //member 저장
        BookclubMember member = new BookclubMember();
        member.addMember(clubId,loginId, ClubAuth.CLUB_OWNER.getCode(), LocalDateTime.now(), loginId, loginId);

        bookclubMemberRepository.save(member);

        /*
         * Todo:원하는 도서 리스트 저장
         */

        return new ResponseEntity<>(clubId, HttpStatus.OK);
    }

    @Override
    @Modifying
    public ResponseEntity<String> editBookclub(BookclubDto bookclubDto) {
        /*
         * add business logic
         * */
        return new ResponseEntity<>("club_id", HttpStatus.OK);
    }

    @Override
    @Modifying
    public ResponseEntity<String> closeBookclub() {
        /*
         * add business logic
         * update Bookclub.closedYn
         * */
        return new ResponseEntity<>("closed", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookclubDto> getBookclubDetail(String clubId) {
        BookclubDto bookclubDto = new BookclubDto();
        Bookclub bookclub = bookclubRepository.findById(clubId)
                .orElseThrow(()->new NotFoundException("There is no book Club."));

        bookclubDto.setClubId(bookclub.getClubId());
        bookclubDto.setClubNm(bookclub.getClubNm());
        bookclubDto.setClubLoc(bookclub.getClubLoc());
        bookclubDto.setTotMemberCnt(bookclub.getClubMemberCnt());
        bookclubDto.setPrivateYn(bookclub.getPrivateYn());
        bookclubDto.setClubIntro(bookclub.getClubIntro());
        bookclubDto.setFileName(bookclub.getFileName());
        bookclubDto.setFileExt(bookclub.getFileExt());
        bookclubDto.setThumbnail(postFileService.getDownloadFileUri(bookclub.getThumbnail()));

        List<BookclubMember> bookclubMembers = bookclub.getBookclubMembers();
        BookclubMember owner = bookclub.getBookclubMembers()
                .stream()
                .filter(bookclubMember -> ClubAuth.CLUB_OWNER.getCode().equals(bookclubMember.getClubAuth()))
                .findFirst()
                .orElseGet(() -> bookclubMemberRepository.findBookclubMemberByUsername(bookclub.getCreatedBy()).get());
        bookclubDto.setOwner(owner);
        bookclubDto.setMemberCnt(bookclubMembers.stream().count());

        return new ResponseEntity<>(bookclubDto, HttpStatus.OK);
    }
}
