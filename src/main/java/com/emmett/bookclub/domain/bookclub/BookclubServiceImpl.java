package com.emmett.bookclub.domain.bookclub;

import com.emmett.bookclub.domain.bookpost.files.PostFilesService;
import com.emmett.bookclub.domain.model.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookclubServiceImpl implements BookclubService {
    private final BookclubRepository bookclubRepository;
    private final PostFilesService postFilesService;

    @Override
    public ResponseEntity<List<BookclubDto>> getBookclubList() {
        List<BookclubDto> list = bookclubRepository.findBookclubList()
                .stream()
                .map(bookclub ->{
                    BookclubDto bookclubDto = new BookclubDto(bookclub);
                    bookclubDto.setThumbnail(postFilesService.getDownloadFileUri(bookclubDto.getThumbnail()));
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
        /*
        * add business logic
        * */
        return new ResponseEntity<>("club_id", HttpStatus.OK);
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
    public ResponseEntity<Map<String, Object>> getBookclubDetail(String clubId) {
        Map<String, Object> result = new HashMap<>();

        Optional<Bookclub> bookclub = bookclubRepository.findById(clubId);
        if (bookclub.isPresent()) {
            result.put("clubId", bookclub.get().getClubId());
            result.put("clubNm", bookclub.get().getClubNm());
            result.put("clubLoc", bookclub.get().getClubLoc());
            result.put("clubMemberCnt", bookclub.get().getClubMemberCnt());
            result.put("privateYn", bookclub.get().getPrivateYn());
            result.put("clubIntro", bookclub.get().getClubIntro());
            result.put("fileName", bookclub.get().getFileName());
            result.put("fileExt", bookclub.get().getFileExt());
            result.put("thumbnail", bookclub.get().getThumbnail());
        } else {
            throw new RuntimeException("There is no book Club.");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
