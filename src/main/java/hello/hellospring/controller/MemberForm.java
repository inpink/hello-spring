package hello.hellospring.controller;

public class MemberForm { //★createMemberform.html에서 보낸 데이터를 받고 사용하기 위해 사용자 정의 클래스가 필요하다!!
    private String name; //createMemberform.html에서 <form>에서 name이라는 이름으로 데이터를 넘겨주었다!
    //받은 데이터가 위의 name에 저장된다. Getter Setter(단축키ALT+INSERT)로 데이터를 보내고 수정할 수 있다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
