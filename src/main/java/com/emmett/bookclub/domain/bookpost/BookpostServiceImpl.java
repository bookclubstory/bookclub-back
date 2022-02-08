package com.emmett.bookclub.domain.bookpost;

import com.emmett.bookclub.domain.bookpost.files.PostFilesService;
import com.emmett.bookclub.domain.model.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        List<BookpostDto> list = bookpostRepository.getBookpostRprsImgList()
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
                ).collect(Collectors.collectingAndThen(Collectors.toList(), result->{
                    if(result.isEmpty())throw new NotFoundException("해당 게시물이 존재하지 않습니다.");
                    return result;
                }));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<BookpostDto>> getBookpostList(Pageable pageable) {
        Page<Object[]> objects = bookpostRepository.getBookpostRprsImgList(pageable);
        List<BookpostDto> list = objects
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
                ).collect(Collectors.collectingAndThen(Collectors.toList(), result->{
                    if(result.isEmpty())throw new NotFoundException("해당 게시물이 존재하지 않습니다.");
                    return result;
                }));

        Page<BookpostDto> page = new PageImpl<>(list, pageable, objects.getTotalElements());
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
