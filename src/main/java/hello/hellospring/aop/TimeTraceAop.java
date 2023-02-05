package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //@Aspect 어노테이션을 붙여서 AOP로 사용할 것임을 명시
@Component //위에 @Component를 달아서 '스프링 빈'에 등록해줘도 되지만,
//보통은 SpringConfig에서 @Bean 생성자로 등록해준다. (읽는 사람이 AOP를 사용했다고 알기 쉽게 하기 위함)
//여기서는 간단하게 @Component로 달아줌.
public class TimeTraceAop {

    //@Around로 적용 범위 지정해줌! (공통 관심 사항 -> 핵심 관심 사항들)
    //@Around에서 지정된 것들이 '호출 될 때마다' joinPoint로 다양한 데이터들을 넘겨줌.
    //호출할 범위는 Docs보면서 쓰면 어렵지 않음! 아래에서는 hello.hellospring안에 있는 모든 것들에 대해 적용해준다고 되어 있음.
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {  //마찬가지로 이부분도 Docs보면서 쓰면 어렵지 않음! 큰 틀은 가져다 쓰는 느낌
        long start=System.currentTimeMillis(); //현재 시간 밀리초로 기록
        System.out.println("START: "+joinPoint.toString()); //jointPoint를 string형태로 출력. List org.springframework.data.repository.ListCrudRepository.findAll()같은 것들이 출력됨
        try{ //try-finally 예외처리
            return joinPoint.proceed(); //joinPoint.proceed는 jointPoint 비지니스 메소드를 실행한다. 실행이 끝나면 'Object'형으로 반환한다!
            //이 Object에는 비지니스 메소드가 실행된 결과 데이터들이 담겨있음.
        } finally { //try-finally. try에 있는 내용이 끝났을 때 finally 실행(자바 문법)
            long finish=System.currentTimeMillis(); //종료했을 때 현재 시간 밀리초로 기록
            long timeMs=finish-start; //걸린 시간 밀리초로 계산
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms"); //출력
        }
    }
}
