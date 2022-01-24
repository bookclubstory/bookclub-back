package com.emmett.bookclub.domain.bookclub.session;

import com.emmett.bookclub.domain.bookclub.Bookclub;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookclubSessionServiceImpl implements BookclubSessionService {
    private final BookclubSessionRepository bookclubSessionRepository;

    @Override
    public ResponseEntity<List<Map<String, Object>>> getBookclubSessionList() {
        return null;
    }

    @Override
    @Modifying
    public ResponseEntity<String> addBookclubSession(BookclubSessionDto bookclubSessionDto) {
        return null;
    }

    @Override
    @Modifying
    public ResponseEntity<String> editBookclubSession(BookclubSessionDto bookclubSessionDto) {
        return null;
    }

    @Override
    @Modifying
    public ResponseEntity<String> closeBookclubSession() {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBookclubSessionDetail(String sessionId) {
        Map<String, Object> result = new HashMap<>();

        Optional<BookclubSession> bookclubSession = bookclubSessionRepository.findById(sessionId);
        if (bookclubSession.isPresent()) {
            result.put("clubId", bookclubSession.get().getClubId());
            result.put("sessionId", bookclubSession.get().getBookclubSessionId());
            result.put("sessionNm", bookclubSession.get().getSessionNm());
            result.put("beginDt", bookclubSession.get().getBeginDt());
            result.put("beginPlc", bookclubSession.get().getBeginPlc());
            result.put("allowedCnt", bookclubSession.get().getAllowedCnt());
            result.put("sessionContent", bookclubSession.get().getSessionContent());
            result.put("closedYn", bookclubSession.get().getClosedYn());
        } else {
            throw new RuntimeException("There is no session.");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
