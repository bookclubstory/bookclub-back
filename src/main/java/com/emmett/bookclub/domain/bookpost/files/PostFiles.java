package com.emmett.bookclub.domain.bookpost.files;

import com.emmett.bookclub.domain.bookpost.Bookpost;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "TB_BOARD_FILE")
public class PostFiles implements Serializable {
    @Id
    @Column(name = "board_file_id")
    int boardFileId;

    @Column(name = "post_id")
    String postId;

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

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", insertable = false, updatable = false)
    @JsonIgnore
    Bookpost bookpost;

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
