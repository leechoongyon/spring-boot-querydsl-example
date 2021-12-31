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

    @Before
    public void setUp() {
        Member member = Member.builder()
                .name("test")
                .build();
        memberRepository.save(member);
    }

    @Test
    public void member_querydsl_테스트 () throws Exception {
        List<Member> list = memberQueryRepository.findByName("test");
        Assert.assertEquals(1, list.size());
    }
}
