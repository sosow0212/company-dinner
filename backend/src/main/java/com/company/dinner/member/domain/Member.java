package com.company.dinner.member.domain;

import com.company.dinner.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
public class Member extends BaseEntity {

    private static final int EMAIL_MASKING_LENGTH = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String imageUrl;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MemberRole memberRole;

    public boolean isAdmin() {
        return memberRole.equals(MemberRole.ADMIN);
    }

    public void updateRole(final MemberRole memberRole) {
        this.memberRole = memberRole;
    }
}
