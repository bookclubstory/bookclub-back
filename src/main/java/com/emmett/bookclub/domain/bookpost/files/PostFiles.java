package com.emmett.bookclub.domain.bookpost.files;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@IdClass(PostFilesKey.class)
@RequiredArgsConstructor
@Table(name = "TB_BOARD_FILE")
public class PostFiles {
    @Id
    @Column(name = "board_file_id")
    int boardFileId;

    @Id
    @Column(name = "post_id")
    String postId;

    @Id
    @Column(name = "attach_id")
    String attachId;

    @Column(name = "file_name")
    String fileName;

    @Column(name = "file_type")
    String fileType;

    @Column(name = "file_ext")
    String fileExt;

    @Column(name = "thumbnail")
    String thumbnail;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    // default constructor
    public PostFiles(int boardFileId, String postId, String attachId, String fileName, String fileType, String fileExt, String thumbnail, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.boardFileId = boardFileId;
        this.postId = postId;
        this.attachId = attachId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileExt = fileExt;
        this.thumbnail = thumbnail;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
}
