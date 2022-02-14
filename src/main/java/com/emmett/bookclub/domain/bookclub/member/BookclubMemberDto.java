package com.emmett.bookclub.domain.bookclub.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface BookclubMemberDto {
    Integer getMemberId();
    String getClubId();
    String getUsername();
    String getLastName();
    String getFirstName();
    String getEmail();
    String getClubAuth();
    String getValue();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getClubJoinDate();
    Integer getSessionCnt();
}
