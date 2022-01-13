package com.example.Anarchy.domain.model;

import lombok.Getter;

@Getter
public enum PermissaoEnum {
    ADMIN(new Long[]{1L, 2L}), USER(new Long[]{2L});
    private final Long[] roleID;

    PermissaoEnum(Long[] roleID) {
        this.roleID = roleID;
    }
}
