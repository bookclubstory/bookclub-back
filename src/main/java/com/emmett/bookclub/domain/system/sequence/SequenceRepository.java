package com.emmett.bookclub.domain.system.sequence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, String> {
    Optional<Sequence> findBySeqPrefix(String dataset);
}
