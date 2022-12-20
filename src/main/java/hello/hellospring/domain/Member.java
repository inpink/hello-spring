package hello.hellospring.domain;

public class Member { //Member class 정의(회원 객체 만들기 위해)
    private Long id; //회원구분id와 이름name을 멤버 변수로 사용
    private String name;

    public Long getId() { //기본적인 getter setter을 넣어줌(Alt+Insert 사용)
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
