package me.wane.jpa.relationship_mapping.one;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
//@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    //Hibernate: create table member (id bigint not null auto_increment, team_id bigint, username varchar(255), primary key (id)) engine=InnoDB
    @JoinColumn(name = "team_id")  //외래키를 매핑할 때 사용, name 속성은 매핑할 외래키 이름을 지정, 이 어노테이션은 생략 가능
    private Team team;

    public Member(String username) {
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
