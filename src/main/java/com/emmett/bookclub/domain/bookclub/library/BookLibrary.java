package com.emmett.bookclub.domain.bookclub.library;

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
@Table(name = "TB_LIBRARY")
public class BookLibrary {
    @Id
    @Column(name = "library_id")
    int libraryId;

    @Id
    @Column(name = "club_id")
    String clubId;

    @Column(name = "session_id")
    String sessionId;

    @Column(name = "book_nm")
    String bookNm;

    @Column(name = "book_author")
    String bookAuthor;

    @Column(name = "book_api")
    String bookApi;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    // default constructor
    public BookLibrary(int libraryId, String clubId, String sessionId, String bookNm, String bookAuthor, String bookApi, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.libraryId = libraryId;
        this.clubId = clubId;
        this.sessionId = sessionId;
        this.bookNm = bookNm;
        this.bookAuthor = bookAuthor;
        this.bookApi = bookApi;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    //TODO:// add @Builder constructor
}
