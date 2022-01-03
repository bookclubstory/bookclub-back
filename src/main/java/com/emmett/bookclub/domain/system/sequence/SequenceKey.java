package com.emmett.bookclub.domain.system.sequence;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Getter
@NoArgsConstructor
public class SequenceKey implements Serializable {
    private String seqType;
    private String seq;

    @Builder
    public SequenceKey(String seqType, String seq) {
        this.seqType = seqType;
        this.seq = seq;
    }
}
