package com.company.dinner.member.domain;

import com.company.dinner.member.exception.exceptions.RoleNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberRoleTest {

    @Test
    void 권한이_성공적으로_생성된다() {
        // given
        String role = "member";

        // when
        MemberRole result = MemberRole.from(role);

        // then
        assertThat(result.name().equalsIgnoreCase(role)).isTrue();
    }

    @Test
    void 권한이_없다면_생성할_수_없다() {
        // given
        String invalidRole = "wrong";

        // when & then
        assertThatThrownBy(() -> MemberRole.from(invalidRole))
                .isInstanceOf(RoleNotFoundException.class);
    }
}
