package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //JPA가 관리하는 Entity가 된다.
public class Member { //Member class 정의(회원 객체 만들기 위해)

    //DB에 값을 전달하면 DB가 ID를 자동으로 생성해주는 것을 "Identity 전략'이라고 한다.
    //=> id 변수는 DB가 생성해줄 것이므로, 어노테이션을 달아서 Identity 기능을 하게 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //회원구분id와 이름name을 멤버 변수로 사용
    private String name; //name변수는 우리가 보내줄 것이므로 아무것도 하지 않는다.

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
