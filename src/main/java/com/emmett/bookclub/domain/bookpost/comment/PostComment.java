package com.emmett.bookclub.domain.bookpost.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@IdClass(PostCommentKey.class)
@RequiredArgsConstructor
@Table(name = "TB_BOARD_COMMENT")
public class PostComment {
    @Id
    @Column(name = "board_comment_id")
    int boardCommentId;

    @Id
    @Column(name = "post_id")
    String postId;

    @Id
    @Column(name = "comment_id")
    String commentId;

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

    // Default Constructor
    public PostComment(int boardCommentId, String postId, String commentId, String commentBody, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.boardCommentId = boardCommentId;
        this.postId = postId;
        this.commentId = commentId;
        this.commentBody = commentBody;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
}
