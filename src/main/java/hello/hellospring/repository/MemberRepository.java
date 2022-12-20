package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { //회원 저장소. DB 선택에 따라 바꿔끼울 수 있도록 class가 아닌 interface로 구현!
    Member save(Member member); //save라는 이름의 멤버 함수 선언. 구현은 MemberRepository를 상속받는 class에서 할 것.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
