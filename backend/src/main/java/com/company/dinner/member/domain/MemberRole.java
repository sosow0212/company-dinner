package com.company.dinner.member.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MemberRole {

    MEMBER("member"),
    ADMIN("admin");

    private final String role;

    MemberRole(final String role) {
        this.role = role;
    }

    public static MemberRole from(final String role) {
        return Arrays.stream(values())
                .filter(it -> it.role.equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("권한을 찾을 수 없습니다."));
    }
}
