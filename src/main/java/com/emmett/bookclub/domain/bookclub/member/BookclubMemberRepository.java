package com.emmett.bookclub.domain.bookclub.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookclubMemberRepository extends JpaRepository<BookclubMember, Integer>{
    List<BookclubMember> findByClubId(String clubId);

    @Query(value="" +
            "select" +
            " m.memberId as memberId, " +
            " m.clubId as clubId," +
            " m.username as username," +
            " u.email as email, " +
            " m.clubAuth as clubAuth," +
            " m.clubJoinDate as clubJoinDate, " +
            " coalesce((select count(t) " +
            "           from SessionTimeline t " +
            "           where t.username = m.username " +
            "           group by t.username), 0) as sessionCnt,  " +
            " case m.clubAuth when '"+ClubAuth.Constants.BANNED_CODE+"' then false else true end as isEnabled " +
            "from BookclubMember m " +
            " inner join User u on m.username = u.username " +
            "where m.clubId =:clubId ")
    List<BookclubMemberDto> findBookclubMemberList(@Param("clubId") String clubId);

    @Query(value = "" +
            "select" +
            " m.memberId as memberId, " +
            " m.clubId as clubId," +
            " m.username as username," +
            " u.email as email, " +
            " m.clubAuth as clubAuth," +
            " m.clubJoinDate as clubJoinDate, " +
            " coalesce((select count(t) " +
            "           from SessionTimeline t " +
            "           where t.username = m.username " +
            "           group by t.username), 0) as sessionCnt, " +
            " case m.clubAuth when '"+ClubAuth.Constants.BANNED_CODE+"' then false else true end as isEnabled " +
            "from BookclubMember m " +
            " inner join User u on m.username = u.username " +
            "where m.memberId =:memberId and m.clubId =:clubId")
    Optional<BookclubMemberDto> findBookclubMember(@Param("clubId") String clubId, @Param("memberId") int memberId);

    Optional<BookclubMember> findBookclubMemberByUsername(String username);
}
