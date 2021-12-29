package com.emmett.bookclub.api.g2b.beforeSpec;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface BeforeSpecService {
    List<Map<String, Object>> searchBeforeSpecList(String keyword);
}
