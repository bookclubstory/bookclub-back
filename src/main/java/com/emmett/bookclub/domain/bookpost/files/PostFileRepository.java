package com.emmett.bookclub.domain.bookpost.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostFileRepository extends JpaRepository<PostFile, Integer> {
    Optional<PostFile> findById(Integer boardFileId);

    Optional<PostFile> findByPostIdAndFileOrder(String postId, int fileOrder);
}
