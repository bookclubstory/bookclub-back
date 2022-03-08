package com.emmett.bookclub.domain.bookclub;

import com.emmett.bookclub.domain.bookclub.member.BookclubMember;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "TB_BOOKCLUB")
public class Bookclub implements Serializable {
    @Column(name = "bookclub_id")
    int bookclubId;

    @Id
    @Column(name = "club_id")
    String clubId;

    @Column(name = "club_nm")
    String clubNm;

    @Column(name = "club_loc")
    String clubLoc;

    @Column(name = "club_member_cnt")
    int clubMemberCnt;

    @Column(name = "private_yn")
    Boolean privateYn;

    @Column(name = "club_intro")
    String clubIntro;

    @Column(name = "file_name")
    String fileName;

    @Column(name = "file_ext")
    String fileExt;

    @Column(name = "thumbnail")
    String thumbnail;

    @Column(name = "closed_yn")
    String closedYn;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    List<BookclubMember> bookclubMembers;

    // Default Constructor
    public Bookclub(int bookclubId, String clubId, String clubNm, String clubLoc, int clubMemberCnt, Boolean privateYn, String clubIntro, String fileName, String fileExt, String thumbnail, String closedYn, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.bookclubId = bookclubId;
        this.clubId = clubId;
        this.clubNm = clubNm;
        this.clubLoc = clubLoc;
        this.clubMemberCnt = clubMemberCnt;
        this.privateYn = privateYn;
        this.clubIntro = clubIntro;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.thumbnail = thumbnail;
        this.closedYn = closedYn;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
}
