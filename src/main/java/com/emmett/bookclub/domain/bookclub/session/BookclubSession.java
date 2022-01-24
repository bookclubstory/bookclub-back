package com.emmett.bookclub.domain.bookclub.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "TB_BOOKCLUB_SESSION")
public class BookclubSession {
    @Column(name = "bookclub_session_id")
    int bookclubSessionId;

    @Column(name = "club_id")
    String clubId;

    @Id
    @Column(name = "session_id")
    String sessionId;

    @Column(name = "session_nm")
    String sessionNm;

    @Column(name = "begin_dt")
    String beginDt;

    @Column(name = "begin_plc")
    String beginPlc;

    @Column(name = "allowed_cnt")
    int allowedCnt;

    @Column(name = "session_content")
    String sessionContent;

    @Column(name = "closed_yn")
    Boolean closedYn;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    // Default Constructor
    public BookclubSession(int bookclubSessionId, String clubId, String sessionId, String sessionNm, String beginDt, String beginPlc, int allowedCnt, String sessionContent, Boolean closedYn, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.bookclubSessionId = bookclubSessionId;
        this.clubId = clubId;
        this.sessionId = sessionId;
        this.sessionNm = sessionNm;
        this.beginDt = beginDt;
        this.beginPlc = beginPlc;
        this.allowedCnt = allowedCnt;
        this.sessionContent = sessionContent;
        this.closedYn = closedYn;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
}
