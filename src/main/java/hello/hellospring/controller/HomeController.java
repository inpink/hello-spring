package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Controller를 만들거면 @Controller를 넣어줘야 함.
public class HomeController {
    @GetMapping("test")
    public String home(){
        return "home";
    }
}
