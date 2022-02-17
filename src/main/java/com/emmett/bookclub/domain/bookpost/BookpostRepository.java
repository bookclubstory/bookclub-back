package com.emmett.bookclub.domain.bookpost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookpostRepository extends JpaRepository<Bookpost, String> {
    @Query("select " +
            "b.boardId, " +
            "b.postId, " +
            "b.title, " +
            "f.boardFileId, " +
            "f.fileName," +
            "b.createdBy, " +
            "b.creationDate, " +
            "b.modifiedBy, " +
            "b.modifiedDate " +
            "from Bookpost b left join PostFile f on b.postId = f.postId and f.fileOrder = 1 " +
            "order by b.postId desc")
    List<Object[]> getBookpostRprsImgList();

    @Query("select " +
            "b.boardId, " +
            "b.postId, " +
            "b.title, " +
            "f.boardFileId, " +
            "f.fileName," +
            "b.createdBy, " +
            "b.creationDate, " +
            "b.modifiedBy, " +
            "b.modifiedDate " +
            "from Bookpost b left join PostFile f on b.postId = f.postId and f.fileOrder = 1 " +
            "order by b.postId desc")
    Page<Object[]> getBookpostRprsImgList(Pageable pageable);

    List<Bookpost> findByPostIdContains(String postId);
}
