package com.emmett.bookclub.domain.bookclub.member;

import com.emmett.bookclub.domain.bookclub.Bookclub;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "club_id", insertable = false, updatable = false)
    @JsonIgnore
    Bookclub bookclub;

    public BookclubMember addMember(String clubId, String username, String clubAuth,
                                    LocalDateTime clubJoinDate,  String createdBy, String modifiedBy){
        this.clubId = clubId;
        this.username = username;
        this.clubAuth = clubAuth;
        this.clubJoinDate = clubJoinDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        return this;
    }
}
