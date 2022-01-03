package com.emmett.bookclub.global.security.role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class RolePermissionKey implements Serializable {
    private String role;
    private String permissionCd;

    @Builder
    public RolePermissionKey(String role, String permissionCd) {
        this.role = role;
        this.permissionCd = permissionCd;
    }
}
