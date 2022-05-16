package com.emmett.bookclub.domain.bookclub;

import com.emmett.bookclub.domain.bookclub.member.BookclubMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookclubDto implements Serializable{
    private static final String CLUB_ID = "clubId";
    private static final String CLUB_NM = "clubNm";
    private static final String CLUB_LOC = "clubLoc";
    private static final String MEMBER_CNT = "memberCnt";
    private static final String TOT_MEMBER_CNT = "totMemberCnt";
    private static final String PRIVATE_YN = "privateYn";
    private static final String CLUB_INTRO= "clubIntro";
    private static final String THUMBNAIL = "thumbnail";
    private static final String FILE_NAME = "file_name";
    private static final String FILE_EXT = "file_ext";

    String clubId;
    String clubNm;
    String clubLoc;
    Long memberCnt;
    Integer totMemberCnt;
    Boolean privateYn;
    String clubIntro;
    String thumbnail;
    String fileName;
    String fileExt;

    BookclubMember owner;
    MultipartFile file;

    public BookclubDto(Map<String, Object> values) {
        this.clubId = String.valueOf(Optional.ofNullable(values.get(CLUB_ID)).orElse(""));
        this.clubNm = String.valueOf(Optional.ofNullable(values.get(CLUB_NM)).orElse(""));
        this.clubLoc = String.valueOf(Optional.ofNullable(values.get(CLUB_LOC)).orElse(""));
        this.memberCnt = (Long) Optional.ofNullable(values.get(MEMBER_CNT)).orElse(0);
        this.totMemberCnt = (Integer) Optional.ofNullable(values.get(TOT_MEMBER_CNT)).orElse(0);
        this.privateYn = (Boolean) Optional.ofNullable(values.get(PRIVATE_YN)).orElse(false);
        this.clubIntro = String.valueOf(Optional.ofNullable(values.get(CLUB_INTRO)).orElse(""));
        this.thumbnail = String.valueOf(Optional.ofNullable(values.get(THUMBNAIL)).orElse(""));
        this.fileName = String.valueOf(Optional.ofNullable(values.get(FILE_NAME)).orElse(""));
        this.fileExt = String.valueOf(Optional.ofNullable(values.get(FILE_EXT)).orElse(""));
    }
}
