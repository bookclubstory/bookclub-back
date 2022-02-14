package com.emmett.bookclub.domain.bookclub.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@IdClass(SessionTimelineKey.class)
@Table(name = "TB_SESSION_TIMELINE")
public class SessionTimeline {
    @Id
    @Column(name = "session_timeline_id")
    int sessionTimelineId;

    @Id
    @Column(name = "session_id")
    String sessionId;

    @Column(name = "username")
    String username;

    @Column(name = "join_state")
    String joinState;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    @UpdateTimestamp
    LocalDateTime modifiedDate;
}
