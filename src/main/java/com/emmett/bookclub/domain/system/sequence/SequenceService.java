package com.emmett.bookclub.domain.system.sequence;

import javax.transaction.Transactional;

@Transactional
public interface SequenceService {
    String getSequenceBySeqPrefix(String type);
}
