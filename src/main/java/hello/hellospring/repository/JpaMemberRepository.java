package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository { //마찬가지로 MemberRepository 상속

    //스프링 부트가 만들어주는 EntityManager. Entity로 등록된 Member class를 관리해주는 것임.
     private final EntityManager em;//얘도 마찬가지로 생성자 등을 이용해서 데이터를 넣어줘야 함

    public JpaMemberRepository(EntityManager em2) { //생성자. @AutoWired 생략됨. JpaMemberRepository-> em2 의존관계.
        this.em = em2;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //member을 저장하는 기능! persist를 이용하면, JPA가 자동으로 다 짜서 insert해주고, 위에서 설정해준 ID도 Indentity 기법으로 자동으로 들어가게 된다! 매우 편리..
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //find(Member.class,id) 원형을 보면, 반환값은 Member이고, 첫번째 인자값은 EntityClass, 두번째 값을 이용해서 찾아서 Member 객체를 반환한다는 것을 알 수 있음.
        Member member=em.find(Member.class,id); //id를 이용해서 Member 객체 반환하는 find()
        return Optional.ofNullable(member); //ofNullable로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        //쿼리문에서 :name이라는 매개변수를 사용했다. "name"이라는 매개변수에 String name값을 직접 넣어줌! (setParameter)
        //넣어준 name값으로 Member 객체를 찾아서 List형식으로 반환하게 한다.
        List<Member> result=em.createQuery("select m from Member m where m.name= :name",Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny(); //findAny는 , ☆여러 개 result 리스트 중☆ 가장 먼저 찾은 값을 반환함.
    }

    @Override
    public List<Member> findAll() {
        //두번째 인자인 Member.class형을 리스트 형태로 반환할건데, 뽑아오는 것은 쿼리문 이용함.
        return em.createQuery("select m from Member m",Member.class)
                .getResultList(); //List형으로 결과 반환
    }
}
