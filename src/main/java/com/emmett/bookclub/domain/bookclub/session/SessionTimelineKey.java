package com.emmett.bookclub.domain.bookclub.session;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionTimelineKey implements Serializable {
    int sessionTimelineId;
    String sessionId;

    @Builder
    public SessionTimelineKey(int sessionTimelineId, String sessionId) {
        this.sessionTimelineId = sessionTimelineId;
        this.sessionId = sessionId;
    }
}
