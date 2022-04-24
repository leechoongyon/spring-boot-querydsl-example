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

    /**
     * from 쿼리에 서브 쿼리 넣는 방법
     *
     * 아래와 같은 쿼리를 수행하고 싶으나 querydsl 에서는
     * from 서브 쿼리절을 지원하지 않습니다.
     *
     * select * from (
     *  select * from member
     *  where
     *      xxx
     *      xxx
     *      xxx
     *      and rownum = 1
     * )
     *
     * @return
     */
    public Member findOneOrderByName() {
        List<Member> list = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.name.asc())
                .fetch();
        if (list == null || list.size() == 0) {
            throw new IllegalStateException("list is null");
        }
        return list.get(0);
    }
}