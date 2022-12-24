package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //@Component 하위에 있는 @Service로 이 클래스를 '스프링 빈'에 등록한다! 그러면 자동으로 의존관계 주입이 된다.
public class MemberService { //회원 서비스 기능 개발
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //메모리는 필요하니까 메모리 객체 1개 가져오기
    private final  MemberRepository memberRepository; //DI(의존관계주입)을 하기 위해, new MemoryMemberRepository를 만들지 않고 외부에서 대입받는다.

    //생성자 매개변수를 통해 의존관계를 주입받는다. 새로운 MemoryMemberRepository를 MemberService에서 만들지 않고, 외부에서 만든 것을 이 MemberSerive의 저장소로 이용하겠다는 의미이다.
    @Autowired //이 클래스와 MemorymemberRepository를 와이어로 연결하여 의존관계를 주입해준다. 멤버서비스->멤버리포지토리 ( 멤버 서비스가 멤버 리포지토리 사용)
    public MemberService(MemoryMemberRepository MemorymemberRepository) {
        this.memberRepository=MemorymemberRepository;
    }

    //회원 가입시키는 메쏘드
    public Long join(Member member){ //가입시킬 멤버 객체 member을 매개변수로 받아 가입시킨다. (이름 중복 검사하고 리포에 저장)
        validateDuplicateMember(member); //이름 중복 검사하는 사용자 정의 함수
        memberRepository.save(member); //이름 중복 검사에서 통과한 뒤, 저장소에 member 객체를 저장시켜 가입시킨다.
        return member.getId(); //해당 멤버 id를 반환한다.
    }

    private void validateDuplicateMember(Member member) {
        //member객체를 매개변수로 받아, 저장소에서 이미 이름이 있는지 findByName으로 검사한다. findByName()의 반환타입은 Optional<Member>이다.
        //Optional.ifPresent는, 만약 Optional의 알맹이가 null이 아니라 무언가가 있다면 실행하게 된다.
        //람다 함수를 이용하여, 무언가가 있다면 m으로 던져서 throw new로 반드시 예외를 발생시킨다. 예외 발생 시 여기서 프로그램 종료가 되므로, 위에 Join에서 Save가 되지 않는다.
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회하여 전체 회원 리스트 반환
    public List<Member> findMembers(){ //findAll()의 반환 타입이 List<Member>이므로 이 메쏘드도 반환 타입이 이와 같다
        return memberRepository.findAll();
    }

    //멤버 ID로 회원 1명 있는 지 조회
    //매개변수로 ID를 받아서, 해당 findById로 저장소에 해당 아이디가 있는 지 확인하고, 반환한다. 없는 경우에도 Optional로 감싸져 있기 때문에 null이 반환될 것이다. 문제 없음!
    public Optional<Member> findOne(Long memberId){ //findById의 반환 타입이 Optional<Member>이다.
        return memberRepository.findById(memberId);
    }
}
