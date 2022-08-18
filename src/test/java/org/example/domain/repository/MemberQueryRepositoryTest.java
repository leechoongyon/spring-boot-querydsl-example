package org.example.domain.repository;

import org.example.domain.entity.Member;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
// querydsl 테스트 할려면 SpringBootTest 이용해야 함.
@SpringBootTest
public class MemberQueryRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void release() {
        memberRepository.deleteAll();
    }

    @Test
    public void member_querydsl_테스트 () throws Exception {
        Member member = Member.builder()
                .name("test")
                .build();
        memberRepository.save(member);

        List<Member> list = memberQueryRepository.findByName("test");
        Assertions.assertEquals(1, list.size());
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
        Assertions.assertEquals("test", result.getName());
    }

    @Test
    @DisplayName("where_다이나믹_쿼리_테스트")
    public void where_다이나믹_쿼리_테스트 () throws Exception {
        // given
        Member member = Member.builder()
                .name("test")
                .telNo("123123123")
                .age(15)
                .build();
        memberRepository.save(member);


        // when
        List<Member> list = memberQueryRepository.
                findMemberByDynamicCondition("test", "123123123", 15);

        // then
        Assertions.assertEquals(15, list.get(0).getAge());

    }
}