package com.emmett.bookclub.domain.system;

import com.emmett.bookclub.global.security.role.RoleDto;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface RoleService {
    @Modifying
    @Transactional
    String createRole(RoleDto roleDto);

    List<RoleDto> getRoleList();

    List<Map<String, Object>> searchRoleList(String keyword);

    @Modifying
    @Transactional
    boolean deleteRole(String role);

    List<Map<String, Object>> getUserListByRole(String role);
}
