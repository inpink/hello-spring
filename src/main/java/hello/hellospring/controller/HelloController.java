package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Controller를 만들거면 @Controller를 넣어줘야 함.
public class HelloController {
    @GetMapping("hello") // /hello라는 url으로 연결
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //data라는 이름으로 "hello!!"라는 값을 전달한다!
        return "hello";  //hello.html으로 연결
    }

    @GetMapping("hello-mvc") // /hello-mvc라는 url으로 연결
    //이번에는 파라미터를 받는다. /hello-mvc?name=kkk 이렇게 kkk라는 값을 파라미터로 전달할 수 있다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); //name이라는 이름으로 파라미터 name의 값을 전달한다.
        return "hello-template"; //hello-template.html으로 연결
    }

    @GetMapping("hello-string") // /hello-string이라는 url으로 연결
    @ResponseBody //이를 적으면 viewResolver를 사용하지 않고, HttpMessageConverter가 대신 동작한다. HTTP의 BODY에 직접 접근한다는 뜻이다.
    //이 또한 파라미터를 받기에, /hello-string?name=k1k1k1 으로 파라미터를 전달하는 url을 사용한다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //html 없이, hello k1k1k1 이라는 값을 HTTP의 BODY에 '직접 반환'하게된다.
    }

    @GetMapping("hello-api") // /hello-api라는 url으로 연결
    @ResponseBody //HTPP의 BODY에 직접 접근한s api 방식
    public Hello helloApi(@RequestParam("name") String name) { //파라미터 1개 받음
        Hello hello = new Hello(); //Hello class의 instance 생성
        hello.setName(name); //hello instance의 name값을 파라미터로 받은 값으로
        return hello; //Class의 instance를 반환하면!
        // json 형식(key:value로 이루어진 데이터 구조) 으로 name 데이터가 BODY에 직접 출력된다!
    }
    static class Hello { //Hello Class 정의
        private String name; //멤버 변수 name
        public String getName() { //여기선 안쓰이긴 하는데, name값 반환하는 용도
            return name;
        }
        public void setName(String name) { //name값 설정하는 용도
            this.name = name;
        }
    }
}