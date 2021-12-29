package com.emmett.bookclub.api.platform;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface PlatformService {
    List<Map<String, Object>> getDashboardList(InterfaceDto interfaceDto);
}
