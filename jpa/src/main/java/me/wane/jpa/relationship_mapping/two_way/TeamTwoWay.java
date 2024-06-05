package me.wane.jpa.relationship_mapping.two_way;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
//@Entity
public class TeamTwoWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<MemberTwoWay> members = new ArrayList<>();

    public TeamTwoWay(String name) {
        this.name = name;
    }
}
