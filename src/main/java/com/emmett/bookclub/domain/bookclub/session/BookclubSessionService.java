package com.emmett.bookclub.domain.bookclub.session;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface BookclubSessionService {
    ResponseEntity<List<Map<String, Object>>> getBookclubSessionList();

    ResponseEntity<String> addBookclubSession(BookclubSessionDto bookclubSessionDto);

    ResponseEntity<String> editBookclubSession(BookclubSessionDto bookclubSessionDto);

    ResponseEntity<String> closeBookclubSession();

    ResponseEntity<Map<String, Object>> getBookclubSessionDetail(String sessionId);
}
