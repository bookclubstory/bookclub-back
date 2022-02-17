package com.emmett.bookclub.domain.bookpost;

import com.emmett.bookclub.domain.bookpost.files.PostFile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "view_count")
    int viewCount;

    @Column(name = "like_count")
    int likeCount;

    @Column(name = "author")
    String author;

    @Column(name = "post_date")
    String postDate;

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

    @OneToMany(mappedBy = "bookpost", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<PostFile> postFiles = new ArrayList<>();

    // default constructor
    public Bookpost(int boardId, String postId, String title, String contentType, String content, int likeCount, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.boardId = boardId;
        this.postId = postId;
        this.title = title;
        this.contentType = contentType;
        this.content = content;
        this.likeCount = likeCount;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
    public void addFile(PostFile postFile) {
        this.postFiles.add(postFile);
    }
}
