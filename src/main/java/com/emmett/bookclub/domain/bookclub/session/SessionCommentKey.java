package com.emmett.bookclub.domain.bookclub.session;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionCommentKey implements Serializable {
    int sessionCommentId;
    String sessionId;

    @Builder
    public SessionCommentKey(int sessionCommentId, String sessionId) {
        this.sessionCommentId = sessionCommentId;
        this.sessionId = sessionId;
    }
}
