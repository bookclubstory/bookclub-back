package com.emmett.bookclub.domain.bookclub.library;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BookLibraryKey implements Serializable {
    int libraryId;
    String clubId;

    @Builder
    public BookLibraryKey(int libraryId, String clubId) {
        this.libraryId = libraryId;
        this.clubId = clubId;
    }
}
