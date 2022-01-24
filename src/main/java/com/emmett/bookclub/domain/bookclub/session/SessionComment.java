package com.emmett.bookclub.domain.bookclub.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@IdClass(SessionCommentKey.class)
@Table(name = "TB_SESSION_COMMENT")
public class SessionComment {
    @Id
    @Column(name = "session_comment_id")
    int sessionCommentId;

    @Id
    @Column(name = "session_id")
    String sessionId;

    @Column(name = "comment_body")
    String commentBody;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    // default constructor
    public SessionComment(int sessionCommentId, String sessionId, String commentBody, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.sessionCommentId = sessionCommentId;
        this.sessionId = sessionId;
        this.commentBody = commentBody;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
}
