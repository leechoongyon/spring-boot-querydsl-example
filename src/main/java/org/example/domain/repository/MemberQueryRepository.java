package org.example.domain.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.domain.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Member> findByName(String name) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.name.eq(name))
                .fetch();
    }
}