package com.emmett.bookclub.domain.bookpost.files;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileHandler {
    private final PostFileService postFileService;
    private final PostFileRepository postFileRepository;

    public List<PostFile> parseFileInfo(String postId, List<MultipartFile> multipartFiles) throws IOException {
        // 변환할 파일 리스트
        List<PostFile> postFiles = new ArrayList<>();

        // 파일 유무 체크
        if(CollectionUtils.isEmpty(multipartFiles)) {
            throw new RuntimeException("No Files... Please re-try.");
        } else {
            // 업로드 날짜로 파일명 변환
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            // 프로젝트 디렉터리 내 절대 경로 설정
            // 경로 구분자 : File.separator
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            // 파일 저장 세부 경로 설정
            /*String path = "images" + File.separator + current_date;*/
            String path = "app" + File.separator + "uploads" + File.separator + "images" + File.separator + current_date;
            File file = new File(path);

            // 디렉토리 확인
            if(!file.exists()) {
                boolean makeDirectory = file.mkdirs();
                // 디렉토리 생성 실패
                if(!makeDirectory) {
                    throw new RuntimeException("서버에 파일을 저장하는 중 디렉토리 생성에 실패하였습니다.");
                }
            }

            // 여러 파일 처리
            int fileOrder = 1;
            for(MultipartFile multipartFile: multipartFiles) {
                // 파일 확장자
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                // 파일 확장자 확인
                if(ObjectUtils.isEmpty(contentType)) {
                    throw new RuntimeException("업로드한 파일의 타입을 알 수 없습니다.");
                } else {
                    // filtering jpeg, png
                    if(contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if(contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else {
                        throw new RuntimeException("업로드한 파일의 확장자가 잘못 되었습니다. (jpeg 또는 png가 아닙니다.)");
                    }
                }

                // escape file duplicate case
                String new_file_name = System.nanoTime() + originalFileExtension;

                // create File DTO - 생략
                /*PostFileDto postFileDto = PostFileDto.builder()
                        .orgFileName(multipartFile.getOriginalFilename())
                        .filePath(path + File.separator + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();*/

                /*
                Description:
                  - Post ID -> "A_USER.USER_ID-current_date-Sequence"
                  */
                // TODO: change to default constructor
                int currentBoardFilesCount = postFileRepository.findAll().size();
                PostFile postFile = new PostFile(
                        currentBoardFilesCount,
                        postId,
                        fileOrder,
                        multipartFile.getOriginalFilename(),
                        path + File.separator + new_file_name,
                        multipartFile.getSize()
                );
                fileOrder++;

                // add List
                postFiles.add(postFile);

                // 업로드한 파일 데이터를 지정한 파일에 저장
                file = new File(absolutePath + path + File.separator + new_file_name);
                multipartFile.transferTo(file);

                // 파일 권한 설정
                file.setWritable(true);
                file.setReadable(true);
            }
        }

        return postFiles;
    }
}
