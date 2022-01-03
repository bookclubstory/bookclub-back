package com.emmett.bookclub.domain.system.sequence;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_sequence")
public class Sequence {
    @Id
    @Column(name = "seq_prefix")
    String seqPrefix;

    @Column(name = "seq")
    String seq;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "creation_dt")
    LocalDateTime creationDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "modified_dt")
    LocalDateTime modifiedDate;

    public Sequence newSeq(String seqPrefix, String seq, String createdBy, LocalDateTime creationDate) {
        this.seqPrefix = seqPrefix;
        this.seq = seq;
        this.createdBy = createdBy;
        this.creationDate = creationDate;

        return this;
    }

    public Sequence editSeq(String seqPrefix, String seq, String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.seqPrefix = seqPrefix;
        this.seq = seq;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;

        return this;
    }
}
