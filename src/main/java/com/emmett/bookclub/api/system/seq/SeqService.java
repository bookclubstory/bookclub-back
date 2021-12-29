package com.emmett.bookclub.api.system.seq;

import javax.transaction.Transactional;

@Transactional
public interface SeqService {
    String getSequenceBySeqPrefix(String type);
}
