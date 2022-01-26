package com.emmett.bookclub.domain.bookpost.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostFilesRepository extends JpaRepository<PostFiles, Integer> {
    Optional<PostFiles> findById(Integer boardFileId);
}
