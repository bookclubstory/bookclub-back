package com.emmett.bookclub.domain.bookclub.member;

import lombok.Builder;
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
@Table(name = "TB_BOOKCLUB_MEMBER")
public class BookclubMember {
    @Id
    @Column(name = "member_id")
    int memberId;

    @Column(name = "club_id")
    String clubId;

    @Column(name = "username")
    String username;

    @Column(name = "club_auth")
    String clubAuth;

    @Column(name = "club_join_date")
    LocalDateTime clubJoinDate;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    LocalDateTime modifiedDate;
}
