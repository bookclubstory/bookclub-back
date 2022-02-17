package com.emmett.bookclub.domain.bookpost;

import com.emmett.bookclub.domain.bookpost.files.PostFile;
import com.emmett.bookclub.domain.bookpost.files.PostFileRepository;
import com.emmett.bookclub.domain.bookpost.files.PostFileService;
import com.emmett.bookclub.domain.model.exception.NotFoundException;
import com.emmett.bookclub.domain.model.vo.BookpostFileVo;
import com.emmett.bookclub.domain.bookpost.files.FileHandler;
import com.emmett.bookclub.global.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookpostServiceImpl implements BookpostService {
    private final Util util;
    private final FileHandler fileHandler;
    private final BookpostRepository bookpostRepository;

    private final PostFileService postFileService;
    private final PostFileRepository postFileRepository;

    public ResponseEntity<List<BookpostDto>> getBookpostList() {
        List<BookpostDto> list = bookpostRepository.getBookpostRprsImgList()
                .stream()
                .map(b -> new BookpostDto(
                                (int) b[0],//boardId
                                String.valueOf(Optional.ofNullable(b[1]).orElse("")),//postId
                                String.valueOf(Optional.ofNullable(b[2]).orElse("")),//title
                                String.valueOf(Optional.ofNullable(b[3]).orElse("")),//rprsImage
                                postFileService.getDownloadFileUri(String.valueOf(Optional.ofNullable(b[4]).orElse(""))), //fileName
                                String.valueOf(Optional.ofNullable(b[5]).orElse("")),
                                (LocalDateTime) (Optional.ofNullable(b[6]).orElse(LocalDateTime.now())),
                                String.valueOf(Optional.ofNullable(b[7]).orElse("")),
                                (LocalDateTime) (Optional.ofNullable(b[8]).orElse(LocalDateTime.now()))
                        )
                ).collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new NotFoundException("해당 게시물이 존재하지 않습니다.");
                    return result;
                }));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<BookpostDto>> getBookpostList(Pageable pageable) {
        Page<Object[]> objects = bookpostRepository.getBookpostRprsImgList(pageable);
        List<BookpostDto> list = objects
                .stream()
                .map(b -> new BookpostDto(
                                (int) b[0],//boardId
                                String.valueOf(Optional.ofNullable(b[1]).orElse("")),//postId
                                String.valueOf(Optional.ofNullable(b[2]).orElse("")),//title
                                String.valueOf(Optional.ofNullable(b[3]).orElse("")),//rprsImage
                                postFileService.getDownloadFileUri(String.valueOf(Optional.ofNullable(b[4]).orElse(""))), //fileName
                                String.valueOf(Optional.ofNullable(b[5]).orElse("")),
                                (LocalDateTime) (Optional.ofNullable(b[6]).orElse(LocalDateTime.now())),
                                String.valueOf(Optional.ofNullable(b[7]).orElse("")),
                                (LocalDateTime) (Optional.ofNullable(b[8]).orElse(LocalDateTime.now()))
                        )
                ).collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new NotFoundException("해당 게시물이 존재하지 않습니다.");
                    return result;
                }));

        Page<BookpostDto> page = new PageImpl<>(list, pageable, objects.getTotalElements());
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @Override
    @Modifying
    public ResponseEntity<String> addNewBookpost(BookpostFileVo bookpostFileVo) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(dateTimeFormatter);

        String currentUserId = util.getLoginId();
        List<Bookpost> list = bookpostRepository.findByPostIdContains(currentUserId + currentDate);
        String postId = currentUserId + "-" + currentDate + "-" + list.size();

        List<MultipartFile> files = bookpostFileVo.getFiles();
        List<PostFile> postFileList = fileHandler.parseFileInfo(postId, files);

        // create Bookpost
        Bookpost bookpost = new Bookpost(
                1,
                postId,
                "title",
                "image",
                bookpostFileVo.getContent(),
                0,
                util.getLoginId(),
                LocalDateTime.now(),
                util.getLoginId(),
                LocalDateTime.now()
        );

        bookpostRepository.save(bookpost);

        // Is Files not empty
        if (postFileList.isEmpty()) {
            throw new RuntimeException("첨부한 파일이 유실되었습니다. 다시 시도해 주세요.");
        } else {
            for (PostFile file : postFileList) {
                // 파일 정보 DB 저장
                //bookpost.addFile(postFileRepository.save(file));
                postFileRepository.save(file);
            }
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
