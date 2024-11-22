package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //test생성법 테스트 하고자 하는 부분에서 ctrl+shift+t를 누른 후 메뉴에서 체크
    //@test는 test 실행 가능하게 하는 함
    //테스트는 한글로 사용해도 됨 ex) void 회원가입()
   // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //클리어를 해주기 위해 클리어를 생성한 memberRepository 사용

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        //isEqualsTo();는 동등성 검사 php에서는 단순히 $a == $b 이런식으로 가능함
        //assertThat의 사용 목적 Systemout.println()을 여러번 사용할 번거로움을 줄이기 위해 사용
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }



    @Test
    public void duplic_member_except(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

         /*
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        */
        //then
    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}