package com.emmett.bookclub.domain.bookpost;

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
@Table(name = "TB_BOARD")
public class Bookpost {
    @Column(name = "board_id")
    int boardId;

    @Id
    @Column(name = "post_id")
    String postId;

    @Column(name = "title")
    String title;

    @Column(name = "content_type")
    String contentType;

    @Column(name = "content")
    String content;

    @Column(name = "rprs_image")
    String rprsImage;

    @Column(name = "like_count")
    int likeCount;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    // default constructor
    public Bookpost(int boardId, String postId, String title, String contentType, String content, String rprsImage, int likeCount, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.boardId = boardId;
        this.postId = postId;
        this.title = title;
        this.contentType = contentType;
        this.content = content;
        this.rprsImage = rprsImage;
        this.likeCount = likeCount;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
}
