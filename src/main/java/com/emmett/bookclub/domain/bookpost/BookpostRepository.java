package com.emmett.bookclub.domain.bookpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookpostRepository extends JpaRepository<Bookpost, String> {
}
