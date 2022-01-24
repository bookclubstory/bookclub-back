package com.emmett.bookclub.domain.bookpost.files;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PostFilesKey implements Serializable {
    int boardFileId;
    String postId;
    String attachId;

    @Builder
    public PostFilesKey(int boardFileId, String postId, String attachId) {
        this.boardFileId = boardFileId;
        this.postId = postId;
        this.attachId = attachId;
    }
}
