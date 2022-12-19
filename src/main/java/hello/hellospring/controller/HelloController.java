package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Controller를 만들거면 @Controller를 넣어줘야 함.
public class HelloController {
    @GetMapping("hello") // /hello라는 url으로 연결 (hello적어줘야 함)
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //data라는 이름으로 "hello!!"라는 값을 전달한다!
        return "hello";  //hello.html으로 연결(hello적어줘야 함)
    }
}