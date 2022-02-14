package com.emmett.bookclub.domain.bookclub.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "TB_BOOKCLUB_MEMBER")
public class BookclubMember implements Serializable {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime clubJoinDate;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    LocalDateTime modifiedDate;
}
