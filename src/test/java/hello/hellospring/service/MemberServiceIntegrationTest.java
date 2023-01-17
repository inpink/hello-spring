package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService; //@Autowired를 설정한 메서드가 자동으로 호출되고, 인스턴스가 자동으로 주입됨. (스프링 자동 의존관계 주입)
    @Autowired MemoryMemberRepository memberRepository;


    // join()함수를 테스트한다. 50%짜리 테스트이다.
    @Test
    void join() {
        //Given
        Member member1 = new Member(); //멤버 객체 하나 만들고, 이름을 Spring으로.
        member1.setName("spring");

        //When (memberService 객체에다가 join으로 member1을 가입시켜본다. join함수의 반환값은 member ID로 해두었다. 이 값을 저장해둔다.)
        Long saveId=memberService.join(member1); //Join안에 중복검사->save가 있다!

        //Then (join에서 반환된 가입시킨 멤버의 ID로 findBYID를 한다. get()으로 Optional 안의 알맹이(member 객체)를 꺼내서, 멤버 객체를 따로 저장해둔다.
        Member findMember= memberRepository.findById(saveId).get();
        assertThat(member1.getName()).isEqualTo(findMember.getName()); //assertThat으로 , 만든 member1의 이름과  ★join을 한 뒤 저장소에서 꺼낸★ 멤버의 이름을 비교한다.
        //다를 경우, 에러가 뜨며 테스트가 실패한다.
    }

    //join()메쏘드에서, IllegalStateException 예외가 발생하는 경우도 검사하는 TEST가 필요하다!
    @Test
    void join_Execption(){
        //Given (멤버 객체 2개 만들고 이름 각각 지정해준다)
        Member member1=new Member();
        member1.setName("Spring1");

        Member member2=new Member();
        member2.setName("Spring1");

        //When (join으로 member1을 memberService에 넣을 때는 에러가 안 떠야 한다.)
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                ()->memberService.join(member2)); //member2를 넣을 때는 중복 이름이므로 에러가 떠야 한다!
        //assertThrows(예외.class,()->예외가 뜰 함수)는, memberService.join(member2)를 실행했을 때 IllegalStateException가 뜬다면 OK고, 그렇지 않다면 ★에러를 발생★시킨다!
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //e.getMessage()를 통해, 발생한 예외에서의 Message를 가져온 뒤, 내가 원했던 에러의 메세지인지 isEqualTo로 비교한다!
    }

    //findMembers와 findOne은 테스트하지 않는다.

}