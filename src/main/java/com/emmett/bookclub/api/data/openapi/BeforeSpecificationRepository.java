package com.emmett.bookclub.api.data.openapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeforeSpecificationRepository extends JpaRepository<BeforeSpecification, BeforeSpecificationKey> {
    List<BeforeSpecification> findByBfSpecRgstNoContainsOrPrdctClsfcNoNmContainsOrRlDminsttNmContainsOrderByRcptDtDesc(String keyword, String keyword1, String keyword2);
}
