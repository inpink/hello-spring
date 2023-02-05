package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스가 인터페이스 상속받을 땐, 기존 class에서 implements 했던것과 다르게 extends를 씀. (자바 문법)
//JpaRepository<Member,Long>는 인터페이스로, '스프링 데이터 jpa' 에서 제공해주는 것. 첫 번째 인자는 '엔티티 클래스', 두 번째 인자는 'ID의 데이터 type'을 뜻한다.
//또, 여느 repository와 마찬가지로 MemberRepository interface를 상속받는다.
//★ JpaRepository를 상속받고 있으면, 스프링 데이터 JPA가 자동으로 구현체를 만들어서 ☆스프링 빈에 등록☆해준다.
//==>  SpringConfig에서 MemberRepository memberRepository()를 Bean에 등록할 필요가 없으며, MemberRepository memberRepository를 생성자 등을 이용하여 바로 스프링으로부터 주입받아 상수로 사용할 수 있다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    //===> 이전 Repository들과 다르게 구현할 것이 없다! 이렇게만 쓰면 기능완료...? ㅋ
    @Override
    Optional<Member> findByName(String name);

}
