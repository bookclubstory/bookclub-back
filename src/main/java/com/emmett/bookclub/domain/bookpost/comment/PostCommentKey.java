package com.emmett.bookclub.domain.bookpost.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PostCommentKey implements Serializable {
    int boardCommentId;
    String postId;
    String commentId;

    @Builder
    public PostCommentKey(int boardCommentId, String postId, String commentId) {
        this.boardCommentId = boardCommentId;
        this.postId = postId;
        this.commentId = commentId;
    }
}
