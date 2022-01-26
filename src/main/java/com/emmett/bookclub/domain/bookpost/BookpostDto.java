package com.emmett.bookclub.domain.bookpost;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookpostDto implements Serializable {
    int boardId;
    String postId;
    String title;
    String rprsImage;
    String rprsImageUrl;
    String createdBy;
    LocalDateTime creationDate;
    String modifiedBy;
    LocalDateTime modifiedDate;

    public BookpostDto(int boardId, String postId, String title, String rprsImage, String rprsImageUrl,
                       String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate){
        this.boardId = boardId;
        this.postId = postId;
        this.title = title;
        this.rprsImage = rprsImage;
        this.rprsImageUrl = rprsImageUrl;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }
}
