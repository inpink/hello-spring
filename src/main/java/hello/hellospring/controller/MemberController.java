package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller는 @Component 하위로, '컴포넌트 스캔'을 이용하여 '스프링 빈'에 자동 등록할 때도 쓰인다.
@Controller //Controller를 만들거면 @Controller를 넣어줘야 함.
public class MemberController {
    private final MemberService memberService; //new해서 만들지 않고, 외부에서 의존관계를 주입받는다.
    // 이 때, 의존관계 주입을 '스프링 컨테이너'에게 맡긴다!

    @Autowired //MemberController 생성자에 Autowired를 달아서, memberService와 와이어로 연결한다. 이를 위해선 memberService가 @Component 하위에 등록돼 있어야 한다.
    // ( 멤버컨트롤러가 멤버 서비스를 사용하므로, 멤버컨트롤러 -> 멤버서비스 , 의존관계 )
    public MemberController(MemberService memberService) { //생성자 매개변수로 memberService를 받아와서 사용한다.
        this.memberService = memberService;
    }
}
