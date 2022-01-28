package com.emmett.bookclub.domain.bookpost;

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
            "b.rprsImage, " +
            "f.fileName," +
            "b.createdBy, " +
            "b.creationDate, " +
            "b.modifiedBy, " +
            "b.modifiedDate " +
            "from Bookpost b left join PostFiles f on b.postId = f.postId and b.rprsImage = f.attachId " +
            "order by b.postId desc")
    List<Object[]> getBookpostRprsImgList();
}
