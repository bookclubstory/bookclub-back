package com.emmett.bookclub.domain.bookpost;

import com.emmett.bookclub.domain.bookpost.files.PostFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookpostServiceImpl implements BookpostService {
    private final BookpostRepository bookpostRepository;

    private final PostFilesService postFilesService;

    public ResponseEntity<List<BookpostDto>> getBookpostList() {
        List<BookpostDto> result= bookpostRepository.getBookpostRprsImgList()
                .stream()
                .map(b->new BookpostDto(
                        (int)b[0],//boardId
                        String.valueOf(Optional.ofNullable(b[1]).orElse("")),//postId
                        String.valueOf(Optional.ofNullable(b[2]).orElse("")),//title
                        String.valueOf(Optional.ofNullable(b[3]).orElse("")),//rprsImage
                        postFilesService.getDownloadFileUri(String.valueOf(Optional.ofNullable(b[4]).orElse(""))), //fileName
                        String.valueOf(Optional.ofNullable(b[5]).orElse("")),
                        (LocalDateTime)(Optional.ofNullable(b[6]).orElse(LocalDateTime.now())),
                        String.valueOf(Optional.ofNullable(b[7]).orElse("")),
                        (LocalDateTime)(Optional.ofNullable(b[8]).orElse(LocalDateTime.now()))
                        )
                ).collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
