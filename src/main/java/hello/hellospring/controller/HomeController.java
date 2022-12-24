package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //Controller를 만들거면 @Controller를 넣어줘야 함.
public class HomeController {
    @GetMapping("/") //'/'은, ULR은 localhost:8080 <- 기본 페이지로 연결해준다.
    public String home(){
        return "home"; //반환값으로 home.html과 연결한다.
    }

}
