package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//MemberRepository interface를 상속받아, 개발 초기단계에 가볍게 메모리를 저장소로 이용하는 MemoryMemberRepository 클래스 생성
public class MemoryMemberRepository implements MemberRepository{
    //Alt+Enter해서 implememts method하여 구현해야 할 4개 함수 Override.

    private static Map<Long,Member> store=new HashMap<>(); //회원id, 회원객체를 묶어서 여러 개를 저장하는  맵을 생성
    private static Long sequence=0L; //회원 번호를 할당하기 위해 사용하는 index 용도

    @Override
    public Member save(Member member) { //새로운 멤버 생성하는 save 메쏘드
        member.setId(++sequence); //현재 시퀀스+1을 해당 회원 객체의 멤버 변수 중 id로 할당
        store.put(member.getId(),member); //store 맵에 <id:해당 회원객체> 넣는다.
        return member; //해당 회원 객체 반환
    }

    @Override
    public Optional<Member> findById(Long id) { //id로 멤버 객체 return하는 findById 메쏘드
        return Optional.ofNullable(store.get(id)); //hashmap.get(key)아이디는 맵에서 key에 해당하는 value를 찾아 반환한다!
    }

    @Override
    public Optional<Member> findByName(String name) { //name으로 멤버 객체 return하는 findByName함수
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //람다 함수(->) 이용하여, 루프를 돌며 name에 해당하는 멤버 객체를 반환한다.
        // 전체.stream().filter() 은 원하는 요소를 뽑아낼 때 많이 쓰는 방식임. 람다 함수를 이용하였으며, findAny()는 하나를 찾으면 종료하게 됨.
        //(정말 간단한 기능만을 구현하기에 동명이인 등의 이슈를 구현하는 구체적인 방법은 차차..)
        //store 맵에 있는 값들-values()에 대해 filter()함수를 사용하여, member라는 이름을 사용한 람다식으로 name과 같은-equal()것을 하나만-findAny() 찾아 반환함
        //(맵.values()를 stream filter 돌렸으니 당연히 마지막엔 멤버 객체가 return 됨)
    }

    @Override
    public List<Member> findAll() { //모든 멤버 객체를 반환하는 findAll()함수 정의. ★반환 타입이 ArrayList가 아니라 List이다!
        return new ArrayList<>(store.values()); //'리스트'에 맵에 있는 모든 멤버 객체들-values()를 '담아서 반환!'
        //실무에선 리스트 많이 쓰인다.
    }

    public void clearStore(){
        store.clear(); //맵을 텅 비워주는 clear() 메쏘드
    }
}
