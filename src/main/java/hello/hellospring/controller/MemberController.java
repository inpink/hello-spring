package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/members/new") // /members/new url과 연결. 회원가입 창을 띄움.
    public String createForm(){
        return "members/createMemberForm"; //members 패키지의 createMemberForm.html과 연결한다.
    }

    @PostMapping(value = "/members/new") //해당 링크로 post방식으로 보낸 데이터를 받아오기 위해 @PostMapping를 사용함.
    public String create(MemberForm form){ //보낸 데이터를 사용할 수 있는 사용자 정의 클래스 MemberForm을 이용함. name이라는 키 데이터를 보냈음.
        Member member =new Member();
        member.setName(form.getName()); //key name을 getter로 가져와서 새로운 멤버 객체의 이름으로 저장하고, 밑에서 join()으로 가입시킴!

        System.out.println(member.getName()); //실행 창에 들어온 이름을 확인할 수 있음.

        memberService.join(member);

        return "redirect:/"; //다 끝난 다음에는, redirect(지정해준 링크로 다시 돌려보냄)해줌. 돌려보낼 링크는 /으로, 기본 localhost:8080이다.
    }
}
