package me.wane.jpa.hibernate_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
//@Entity
public class Member {

    @Id
    private Long id;
    @Column(name= "name")
    private String username;
    private int age;
}
