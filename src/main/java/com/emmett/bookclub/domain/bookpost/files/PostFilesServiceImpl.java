package com.emmett.bookclub.domain.bookpost.files;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostFilesServiceImpl implements PostFilesService {
    private final PostFilesRepository postFilesRepository;
}
