package com.company.dinner.fixture;

import com.company.dinner.member.domain.Member;
import com.company.dinner.member.domain.MemberRole;

public class MemberFixture {

    public static Member 어드민_계정_생성() {
        return Member.builder()
                .id(1L)
                .email("admin@admin.com")
                .name("어드민")
                .memberRole(MemberRole.ADMIN)
                .imageUrl("admin_image_url")
                .build();
    }

    public static Member 멤버_계정_생성() {
        return Member.builder()
                .id(2L)
                .email("member@member.com")
                .name("멤버")
                .memberRole(MemberRole.MEMBER)
                .imageUrl("member_image_url")
                .build();
    }
}
