package com.company.dinner.member.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {

    Optional<Member> findById(final Long id);

    Optional<Member> findByEmail(final String email);

    Member save(final Member member);
}
