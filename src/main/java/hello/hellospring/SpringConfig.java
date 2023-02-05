package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //@Component에 포함되는 @Configuration. @Bean도 같이 이용해서 Java로 직접 '스프링 빈'에 객체를 등록해줄 때 쓴다.
public class SpringConfig {

    //★ SpringDataJpaMemberRepository interface가 JpaRepository interace를 상속받아 SpringDataJpaMemberRepository interface가 스프링빈에 자동으로 등록되었으므로,
    // 아래에서 MemberRepository memberRepository()를 Bean으로 등록해주지 않아도 된다!
    // =>  MemberRepository memberRepository()의 return값 없이 바로 스프링으로부터 memberRepository를 생성자 등으로 주입받아!!!상수로 사용할 수 있다.

    // 마찬가지로 생성자를 이용해서 스프링으로부터 MemberRepository를 주입받는다.
    //SpringConfig->MemberRepository 의존성 주입(DI)
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository1){
        this.memberRepository=memberRepository1;
    }

/*     private final EntityManager em; //JPA를 쓰기 위해!

   @Autowired //마찬가지로 SpringConfig ->  EntityManager em2 의존성 주입(DI)
    public SpringConfig(EntityManager em2) {
        this.em=em2;
    }*/

    //JPA에서 EntityManager쓸거라 잠시 주석처리!
    /*private final DataSource dataSource; //DataSource형 상수
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    } //스프링을 통해서 데이터소스를 주입받음
*/
    @Bean //아래 메쏘드의 return값을 '스프링 빈'에 등록해준다!
    public MemberService memberService(){ //등록해줄 객체 Type은 MemberService
        //★아래에서 Bean으로 등록하며 return값을 이용하던 것과 다르게, 상수 memberRepository를 바로 사용할 수 있다!
        return new MemberService(memberRepository); //생성자를 이용해 새로운 MemberService 객체를 만들고, 반환하여 스프링 빈에 등록
        //return new MemberService(memberRepository()); //생성자를 이용해 새로운 MemberService 객체를 만들고, 반환하여 스프링 빈에 등록
    }

/*    @Bean //위와 마찬가지이다. 여기서 2개의 Bean으로 2개를 스프링 빈에 등록해주면
    // 멤버컨트롤러 -> 멤버서비스 -> 멤버리포지토리 이렇게 의존관계 주입이 된다.
    public MemberRepository memberRepository(){
        //return new JdbcMemberRepository(dataSource); //JdbcMemberRepository 사용. 주입받은 데이터소스 보냄!
        //return new MemoryMemberRepository(); //JAVA로 직접 스프링 빈에 등록해주는 것의 장점은, 여기서 return 값을 DBMemberRepository();로 바꿔주면
        //다른 코드 하나 건들일 필요 없이, 의존관계만 바꿔주면 저장소 등을 쉽게 교체할 수 있다는 것이 큰 장점이다! 멤버컨트롤러->멤버서비스->DB리포지토리
        //return new JdbcTemplateMemberRepository(dataSource); //JdbcTemplateMemberRepository 이용.
        return new JpaMemberRepository(em); //JpaMemberRepository 사용. 매개변수로 위에서 DI받은 EntityManager em보내주기.
    }*/

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
