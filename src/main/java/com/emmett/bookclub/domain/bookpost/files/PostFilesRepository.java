package com.emmett.bookclub.domain.bookpost.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFilesRepository extends JpaRepository<PostFiles, PostFilesKey> {
}
