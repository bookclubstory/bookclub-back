package com.emmett.bookclub.domain.system.sequence;

import com.emmett.bookclub.global.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SequenceServiceImpl implements SequenceService {
    private final Util util;
    private final SequenceRepository sequenceRepository;

    @Override
    public String getSequenceBySeqPrefix(String seqType) {
        StringBuilder sequenceId = new StringBuilder();
        Optional<Sequence> seq = sequenceRepository.findBySeqPrefix(seqType);

        String loginId = util.getLoginId();
        if(seq.isPresent()) {
            String prevSeq = seq.get().getSeq();
            int currSeq = Integer.parseInt(prevSeq) + 1;
            int numOfDigits = (int) Math.log10(currSeq) + 1;

            sequenceId.append(seqType + "-");
            for (int i = 0; i < 5 - numOfDigits; i++) {
                sequenceId.append("0");
            }
            sequenceId.append(currSeq);

            // update Seq by seqType
            Sequence editSequence = new Sequence();
            editSequence.editSeq(
                    seqType,
                    String.valueOf(currSeq),
                    seq.get().getCreatedBy(),
                    seq.get().getCreationDate(),
                    loginId,
                    LocalDateTime.now());
            sequenceRepository.save(editSequence);
        } else {
            sequenceId.append(seqType + "-00001");

            // insert new Seq
            Sequence newSequence = new Sequence();
            newSequence.newSeq(
                    seqType,
                    "1",
                    loginId,
                    LocalDateTime.now());
            sequenceRepository.save(newSequence);
        }

        return sequenceId.toString();
    }
}
