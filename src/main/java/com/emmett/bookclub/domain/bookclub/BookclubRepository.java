package com.emmett.bookclub.domain.bookclub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookclubRepository extends JpaRepository<Bookclub, String> {
    @Query("" +
            "select " +
            "   b.clubId as clubId, " +
            "   b.clubNm as clubNm, " +
            "   b.clubLoc as clubLoc, " +
            "   count(m.clubId) as memberCnt, " +
            "   b.clubMemberCnt as totMemberCnt, " +
            "   b.privateYn as privateYn," +
            "   b.clubIntro as clubIntro," +
            "   b.thumbnail as thumbnail " +
            "from Bookclub b " +
            "left outer join BookclubMember m" +
            "   on b.clubId = m.clubId " +
            "group by b.clubId " +
            "order by b.clubId desc")
    List<Map<String, Object>> findBookclubList();
    List<Bookclub> findByClubNmOrClubLocOrClubIntroOrderByClubNmAsc(String clubNm, String clubLoc, String clubIntro);
}
