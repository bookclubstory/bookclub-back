package com.emmett.bookclub.domain.bookclub.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookclubMemberRepository extends JpaRepository<BookclubMember, Integer> {
}
