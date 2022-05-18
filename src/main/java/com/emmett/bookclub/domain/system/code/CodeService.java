package com.emmett.bookclub.domain.system.code;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface CodeService {
    List<Map<String, Object>> getCodeList(String code);
}
