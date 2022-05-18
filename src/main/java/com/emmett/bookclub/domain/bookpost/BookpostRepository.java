package com.emmett.bookclub.domain.bookpost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookpostRepository extends JpaRepository<Bookpost, String> {
    @Query(" SELECT b.boardId" +
            "       ,b.postId" +
            "       ,b.title" +
            "       ,f.boardFileId" +
            "       ,f.fileOrder" +
            "       ,f.fileName" +
            "       ,b.createdBy" +
            "       ,b.creationDate" +
            "       ,b.modifiedBy" +
            "       ,b.modifiedDate" +
            "  FROM Bookpost b LEFT JOIN PostFile f ON b.postId = f.postId AND f.fileOrder = 1" +
            " ORDER BY b.postId DESC")
    List<Object[]> getBookpostRprsImgList();

    @Query(" SELECT b.boardId" +
            "       ,b.postId" +
            "       ,b.title" +
            "       ,f.boardFileId" +
            "       ,f.fileOrder" +
            "       ,f.fileName" +
            "       ,b.createdBy" +
            "       ,b.creationDate" +
            "       ,b.modifiedBy" +
            "       ,b.modifiedDate" +
            "  FROM Bookpost b LEFT JOIN PostFile f ON b.postId = f.postId AND f.fileOrder = 1" +
            " ORDER BY b.postId DESC")
    Page<Object[]> getBookpostRprsImgList(Pageable pageable);

    List<Bookpost> findByPostIdContains(String postId);
}
