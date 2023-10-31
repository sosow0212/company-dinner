package com.company.dinner.member.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static com.company.dinner.fixture.MemberFixture.멤버_계정_생성;
import static com.company.dinner.fixture.MemberFixture.어드민_계정_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberTest {

    @Test
    void 어드민인지_확인한다() {
        // given
        Member member = 어드민_계정_생성();

        // when
        boolean result = member.isAdmin();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 권한을_바꾼다() {
        // given
        Member member = 멤버_계정_생성();
        MemberRole adminRole = MemberRole.ADMIN;

        // when
        member.updateRole(adminRole);

        // then
        assertThat(member.getMemberRole()).isEqualTo(adminRole);
    }
}
