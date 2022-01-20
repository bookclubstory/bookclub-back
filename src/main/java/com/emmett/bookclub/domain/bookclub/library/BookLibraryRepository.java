package com.emmett.bookclub.domain.bookclub.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLibraryRepository extends JpaRepository<BookLibrary, BookLibraryKey> {
}
