package org.example.domain.repository;

import org.example.domain.entity.Member;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberQueryRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Test
    public void member_querydsl_테스트 () throws Exception {
        Member member = Member.builder()
                .name("test")
                .build();
        memberRepository.save(member);

        List<Member> list = memberQueryRepository.findByName("test");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void from_서브쿼리_테스트() throws Exception {
        Member member = Member.builder()
                .name("test")
                .build();
        memberRepository.save(member);

        Member member2 = Member.builder()
                .name("test2")
                .build();
        memberRepository.save(member2);

        Member result = memberQueryRepository.findOneOrderByName();
        Assert.assertEquals("test", result.getName());
    }
}