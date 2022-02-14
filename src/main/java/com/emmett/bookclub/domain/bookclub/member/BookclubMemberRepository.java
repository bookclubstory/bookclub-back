package com.emmett.bookclub.domain.bookclub.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookclubMemberRepository extends JpaRepository<BookclubMember, Integer>{

    List<BookclubMember> findByClubId(String clubId);

    @Query(value = "" +
            "select" +
            " m.memberId as memberId, " +
            " m.clubId as clubId," +
            " m.username as username," +
            " u.email as email, " +
            " m.clubAuth as clubAuth," +
            " c.value as value, " +
            " m.clubJoinDate as clubJoinDate, " +
            " coalesce((select count(t) " +
            "           from SessionTimeline t " +
            "           where t.username = m.username " +
            "           group by t.username), 0) as sessionCnt  " +
            "from BookclubMember m " +
            " inner join User u on m.username = u.username " +
            " inner join Code c on m.clubAuth = c.code and c.upperCode = :upperCode " +
            "where m.clubId =:clubId")
    List<BookclubMemberDto> findBookclubMember(@Param("clubId") String clubId, @Param("upperCode") String upperCode);
}
