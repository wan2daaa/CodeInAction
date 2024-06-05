package me.wane.jpa.relationship_mapping.two_way;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Entity
public class MemberTwoWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    //Hibernate: create table member (id bigint not null auto_increment, team_id bigint, username varchar(255), primary key (id)) engine=InnoDB
    @JoinColumn(name = "team_two_way_id")  //외래키를 매핑할 때 사용, name 속성은 매핑할 외래키 이름을 지정, 이 어노테이션은 생략 가능
    private TeamTwoWay team;


    //연관관계 편의 메소드
    public void setTeam(TeamTwoWay team) {

        if (this.team != null) { //기존에 반대 방향에 값을 채워 넣었으므로, 기존 값을 없애주는 로직이 필요
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this); //읽기 전용이지만, 객체관점에서 값을 채워줘서 더욱 안전하게 사용할 수 있다.
    }

    public MemberTwoWay(String username) {
        this.username = username;
    }



    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}

