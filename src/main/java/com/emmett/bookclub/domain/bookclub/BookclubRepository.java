package com.emmett.bookclub.domain.bookclub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookclubRepository extends JpaRepository<Bookclub, String> {
    List<Bookclub> findByClubNmOrClubLocOrClubIntroOrderByClubNmAsc(String keyword);
}
