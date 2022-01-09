package com.emmett.bookclub.domain.mainpage;

import com.emmett.bookclub.global.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {
    private final Util util;

}
