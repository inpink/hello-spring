package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; //Assertions.asserThat()~함수를 asserThat()~ 으로 줄여쓸 수 있게 해준다.

class MemoryMemberRepositoryTest { //이 클래스 전체를 테스트 돌렸을 때, @Test 메소드들의 실행 순서는 보장할 수 없다!! => 메소드들끼리 의존관계 x
    MemoryMemberRepository repository = new MemoryMemberRepository(); //MemoryMemberRepository 기능을 테스트해본다.

    @AfterEach //아래의 각 메소드들이 종료될 '때마다' 아래 함수가 호출된다.
    public void afterEach(){ //각 메소드가 종료될 때마다 저장소를 텅 비워준다.
        repository.clearStore(); //그래서 각 메소드들의 테스트 결과가 다른 메소드에 영향을 주지 않도록 한다.
        // => 테스트 메소드는 각각 독립적이게, 서로 의존관계 있으면 x
    }

    @Test //@는 Annotation mark임. 이 마크다 달리면, 아래 함수는 Test를 하는 용도가 된다.
    public void save() { //MemoryMemberRepository의 save()함수를 테스트할 것이라 이름을 이렇게 지었는데, 아무렇게 지어도 상관은 없다.
        //given (주어진 옳은 데이터 - Member 클래스로 회원 객체 1개 생성)
        Member member = new Member();
        member.setName("YYHH123");

        //when (테스트해볼 기능- save 메쏘드)
        repository.save(member);

        //then (검증 - 멤버 객체의 id로 저장소의 객체를 꺼내온다. / 여기는 검증 단계이기에 findById는 문제가 없다고 가정한다. 물론 에러가 뜨면 이 부분도 의심은 할 수 있겠지만.. 이 메쏘드에서 주로 검사하는 것은 save함수이다. )
        Member result = repository.findById(member.getId()).get();
        //findById에서는 Optional로 감싸준 member를 반환하고 있음. 여기서 member를 꺼낼려면 get()을 써줘야 함!
        //Optional안의 내용이 null이면  NoSuchElementException 에러가 뜨기에, if문을 이용하여 옵셔널객체.isPresent()가 true일 때 get()해주는 것이 좋다.
        //(여기서는 간단한 예제이고, test를 위한 용도이므로 pass)

        assertThat(result).isEqualTo(member);//Assertions.asserthat(검사할값).isEqualTo(기대값)
        //위에서 import Assertions.* 했기 때문에 assertThat만 적어도 된다.
        //result==member을 해도 되지만, 이랬을 경우 true false가 출력된다.
        // 대신 asserthat.isEqualTo를 이용하면, 같으면 문제 없이 넘어가고, 다르면 '에러'가 떠서 알려준다!
    }

    @Test //Test 용도
    public void findByName(){ //MemoryMemberRepository의 findByName()함수를 검증할 것이라 이름을 이렇게 지음. 아무렇게나 지어도 되긴 함.
        //given (주어진 옳은 데이터로, 회원 객체 2개를 repository(MemoryMemberRepository)에 넣어준다!
        Member member1=new Member();
        member1.setName("fff1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("fff2");
        repository.save(member2);

        //when (repository(MemoryMemberRepository)의 findByName 메소드를 테스트한다.
        //이 또한 Optional로 감싸져 있기에 get()을 사용하고, get으로 벗겨낸 알맹이는 Member 객체 Type이다.
        Member result=repository.findByName("fff1").get();

        //then (동일하게, 꺼낸 객체와 given member1 객체가 같은 지 검사한다.)
        //여기선 member2는 검사하지 않고 있다.
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){ //MemoryMemberRepository의 findAll()함수를 검증할 것이라 이름을 이렇게 지음. 아무렇게나 지어도 되긴 함.
        //given (동일하게, 주어진 옳은 데이터로 회원 객체 2개를 만들어준다. AfterEach에 의해 매 메소드마다 저장소는 초기화 되기에 이 코드를 또 적어주는 것이다.(독립적))
        Member member1=new Member();
        member1.setName("fff1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("fff2");
        repository.save(member2);

        //when
        //findAll()메쏘드의 반환 타입이 List이므로 List로 받아준다.
        //List는 인터페이스, ArrayList는 클래스. 결과는 같으나, List를 이용하는 이유는 '다형성'에 있다.
        // List를 구현한 것 중 일부가 ArrayList인데, 백엔드 개발을 하다보면 ArrayList를 LinkedList 등으로 바꿔야 할 때가 있다. 이 때 타입 변화의 '유연성'을 위함임.
        List<Member> result=repository.findAll();

        //then (여기서는 간단하게 size 개수만 세어줬다.)
        assertThat(result.size()).isEqualTo(2);
    }

}
