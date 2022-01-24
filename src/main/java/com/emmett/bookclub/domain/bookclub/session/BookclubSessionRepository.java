package com.emmett.bookclub.domain.bookclub.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookclubSessionRepository extends JpaRepository<BookclubSession, String> {
}
