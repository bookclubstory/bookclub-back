package com.emmett.bookclub.api.system.seq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeqRepository extends JpaRepository<Seq, String> {
    Optional<Seq> findBySeqPrefix(String dataset);
}
